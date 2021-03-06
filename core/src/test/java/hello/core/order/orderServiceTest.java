package hello.core.order;

import hello.core.AppConfig;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class orderServiceTest {

    // DIP 위반 !
    MemberService memberService;
//            = new MemberServiceImpl();
    OrderService orderService;
//        = new OrderServiceImpl();

    @BeforeEach
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
    }

    @Test
    void createOrder() {
        long memberId = 1L;

        // 회원 생성
        Member member = new Member(memberId, "memberA", Grade.VIP);

        // 회원 가입
        memberService.join(member);

        // 주문 생성
        Order order = orderService.createOrder(memberId, "itemA", 10000);

        // [로그]
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }
}
