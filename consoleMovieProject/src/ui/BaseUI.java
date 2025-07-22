package ui;

import java.util.Scanner;

public abstract class BaseUI {
    protected Scanner sc;

    public BaseUI() {
        sc = new Scanner(System.in);
    }

    // 자식 클래스들이 반드시 구현해야 할 메소드
    public abstract void start();

    // 자식들이 공통으로 사용할 입력 기능
    protected String getString(String msg) {
        System.out.print(msg);
        return sc.nextLine();
    }

    protected int getInt(String msg) {
        // 예외 처리까지 중앙에서 관리
        while (true) {
            try {
                return Integer.parseInt(getString(msg));
            } catch (NumberFormatException e) {
                System.out.println("⚠️ 잘못된 입력입니다. 숫자를 입력해주세요.");
            }
        }
    }
}
