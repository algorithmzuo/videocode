package videocode;

public class Code02_MaxLeftMaxRight {

	public static int solution1(int[] arr) {
		if (arr == null || arr.length < 2) {
			return 0;
		}
		int ans = Integer.MIN_VALUE;
		for (int leftEnd = 0; leftEnd < arr.length - 1; leftEnd++) {
			int leftMax = arr[0];
			for (int i = 1; i <= leftEnd; i++) {
				leftMax = Math.max(leftMax, arr[i]);
			}
			int rightMax = arr[leftEnd + 1];
			for (int i = leftEnd + 2; i < arr.length; i++) {
				rightMax = Math.max(rightMax, arr[i]);
			}
			ans = Math.max(ans, Math.abs(leftMax - rightMax));
		}
		return ans;
	}

	public static int solution2(int[] arr) {
		if (arr == null || arr.length < 2) {
			return 0;
		}
		int max = arr[0];
		for (int i = 1; i < arr.length; i++) {
			max = Math.max(max, arr[i]);
		}
		return max - Math.min(arr[0], arr[arr.length - 1]);
	}

	public static int[] randomArray(int maxLen, int maxValue) {
		int len = (int) (Math.random() * maxLen);
		int[] arr = new int[len];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) (Math.random() * (maxValue + 1)) - (int) (Math.random() * (maxValue + 1));
		}
		return arr;
	}

	public static void main(String[] args) {
		int maxLen = 10;
		int maxValue = 30;
		int testTime = 3000000;
		System.out.println("如果没有错误信息打印，说明所有测试通过");
		System.out.println("测试开始");
		for (int i = 0; i < testTime; i++) {
			int[] arr = randomArray(maxLen, maxValue);
			int ans1 = solution1(arr);
			int ans2 = solution2(arr);
			if (ans1 != ans2) {
				System.out.println("出错了！");
				System.out.println(ans1 + " , " + ans2);
				break;
			}
		}
		System.out.println("测试结束");

	}

}
