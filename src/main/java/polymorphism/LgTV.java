package polymorphism;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component ("tv_L")//빈으로 등록시키기위한 어노테이션 
public class LgTV implements TV{
	@Autowired //필요한 의존 객체의 “타입"에 해당하는 빈을 찾아 주입 하는 스프링에서 지원하는 어노테이션
	//@Inject Java에서 지원하는 어노테이션
	//@Qualifier("sony")
	//@Resource(name="sony")
	private Speaker speaker;
	
	private int price;
	private int amount;
	
	public LgTV() {
		System.out.println("=> Lg TV --- 객체 생성");
	}
	// 생성자 오버로딩
	public LgTV(Speaker speaker) {
		System.out.println("=> Lg TV(2) --- 객체 생성");
		this.speaker = speaker;
	}	
	// 생성자 오버로딩
	public LgTV(Speaker speaker, int price) {
		System.out.println("=> Lg TV(3) --- 객체 생성");
		this.speaker = speaker;
		this.price = price;
	}
	// 생성자 오버로딩
	public LgTV(Speaker speaker, int price, int amount) {
		System.out.println("=> Lg TV(3) --- 객체 생성");
		this.speaker = speaker;
		this.price = price;
		this.amount = amount;
	}
	
	// setter
	public void setSpeaker(Speaker speaker) {
		this.speaker = speaker;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}
	
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	
	// 객체 초기화 메서드
	public void initMethod() {
		System.out.println("Lg TV --- 객체 초기화");
	}
	
	// 객체 삭제 메서드	
	public void destroyMethod() {
		System.out.println("Lg TV --- 객체 삭제");
	}
	@Override
	public void powerOn() {
		System.out.println("Lg TV --- 전원 on (가격 : " + price +", 개수 : "+ amount +")" );
	}
	@Override
	public void powerOff() {
		System.out.println("Lg TV --- 전원 off");
	}
	@Override
	public void volumeUp() {
		//System.out.println("Lg TV --- 볼륨 up");
		speaker.volumeUp();
	}
	@Override
	public void volumeDown() {
		//System.out.println("Lg TV --- 볼륨 down");
		speaker.volumeDown();
	}
}