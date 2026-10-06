import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 가위,바위,보 게임을 하는 클래스
 */
class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String startStr = " ";
//				가위바위보 게임을 시작합니다. 아래 보기 중 하나를 고르세요.
//
//				1. 5판 3승
//				2. 3판 2승
//				3. 1판 1승
//
//				번호를 입력하세요.""";

		System.out.print(startStr);

		int winCount = 0, gameCount = 0;
		String c = br.readLine();
		switch (c) {
		case "1":
			winCount = 3;
			gameCount = 5;
			break;
		case "2":
			winCount = 2;
			gameCount = 3;
			break;
		case "3":
			winCount = 1;
			gameCount = 1;
			break;
		}
		
		int player = 0, com = 0, game = 0;

		while (player < winCount && com < winCount && game < gameCount) {
			System.out.print("가위바위보 중 하나 입력: ");
			String playerRCP = br.readLine();
			int playerNum = 0;

			switch (playerRCP) {
			case "가위":
				playerNum = 1;
				break;
			case "바위":
				playerNum = 2;
				break;
			case "보":
				playerNum = 3;
				break;
			}

			int comNum = (int) (Math.random() * 3) + 1;

			if (playerNum == comNum) {
				System.out.println("비겼습니다!!!");
			} else if ((playerNum % 3) + 1 == comNum) {
				System.out.println("졌습니다!!!");
				com++;
			} else {
				System.out.println("이겼습니다!!!");
				player++;
			}
			
			game++;
		}
		
		if(player > com) {
			System.out.println("###플레이어 승!!!");
		} else if (player == com) {
			System.out.println("###무승부!!!");
		} else {
			System.out.println("###컴퓨터 승!!!");
		}
	}
}
