package polymorphism;

public class SamsungTV implements TV  {
	private Speaker speaker;
	private int price;
	private int amount;
	public SamsungTV() {//생성자
		System.out.println("=> Samsung TV(1) --- 객체 생성");
	}
	
	//생성자 오버로딩
	public SamsungTV(Speaker speaker) {
		System.out.println("=> Samsung TV(2) --- 객체 생성");
		this.speaker = speaker;
	}	
	//생성자 오버로딩
	public SamsungTV(Speaker speaker, int price) {
		System.out.println("=> Samsung TV(3) --- 객체 생성");
		this.speaker = speaker;
		this.price = price;
	}
	
	public SamsungTV(Speaker speaker, int price, int amount) {
		System.out.println("=> Samsung TV(3) --- 객체 생성");
		this.speaker = speaker;
		this.price = price;
		this.amount = amount;
	}
	
	public void setSpeaker(Speaker speaker) {
		System.out.println("=> setSpeaker() 호출");
		this.speaker = speaker;
	}

	public void setPrice(int price) {
		System.out.println("=> setPrice() 호출");
		this.price = price;
	}

	public void setAmount(int amount) {
		System.out.println("=> setAmount() 호출");
		this.amount = amount;
	}

	@Override
	public void powerOn() {
		System.out.println("Samsung TV --- 전원 on. (가격: " + price + ", 개수: "+ amount +")");
	}
	@Override
	public void powerOff() {
		System.out.println("Samsung TV --- 전원 off");
	}
	@Override
	public void volumeUp() {
		//System.out.println("Samsung TV --- 볼륨 up");
		//speaker = new SonySpeaker();
		speaker.volumeUp();
	}
	@Override
	public void volumeDown() {
		//System.out.println("Samsung TV --- 볼륨 down");
		//speaker = new SonySpeaker();
		speaker.volumeDown();
	}
	
	
	
	// 객체 초기화 메서드
		public void initMethod() {
			System.out.println("Samsung TV --- 객체 초기화");
		}
		
		// 객체 삭제 메서드	
		public void destroyMethod() {
			System.out.println("Samsung TV --- 객체 삭제");
		}
}
