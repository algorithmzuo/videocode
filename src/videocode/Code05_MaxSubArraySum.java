package videocode;

public class Code05_MaxSubArraySum {

	public static int maxSubArraySum1(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		int N = arr.length;
		int ans = Integer.MIN_VALUE;
		for (int L = 0; L < N; L++) {
			for (int R = L; R < N; R++) {
				// arr[L..R]
				int sum = 0;
				for (int i = L; i <= R; i++) {
					sum += arr[i];
				}
				ans = Math.max(ans, sum);
			}
		}
		return ans;
	}

	public static int maxSubArraySum2(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		int N = arr.length;
		// dp[i] : 子数组必须以arr[i]结尾的时候，子数组的最大累加和是多少？
		int[] dp = new int[N];
		dp[0] = arr[0]; // 0..0
		int ans = arr[0];
		// dp[1] -> dp[0]
		// dp[2] -> dp[1]
		// dp[3] -> dp[2]
		// dp[4] -> dp[3]
		// dp[i] => dp[i-1]
		for (int i = 1; i < N; i++) {
			dp[i] = Math.max(arr[i], arr[i] + dp[i - 1]);
			ans = Math.max(ans, dp[i]);
		}
		return ans;
	}

	public static int maxSubArraySum3(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		int N = arr.length;
		// pre -> dp[0]
		int pre = arr[0];
		int ans = arr[0];
		for (int i = 1; i < N; i++) {
		//  dp[3]                           dp[2]
			pre = Math.max(arr[i], arr[i] + pre);
			ans = Math.max(ans, pre);
		}
		return ans;
	}

	public static int[] randomArray(int maxLen, int maxValue) {
		int len = (int) (Math.random() * (maxLen + 1));
		int[] arr = new int[len];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) (Math.random() * (maxValue + 1)) - (int) (Math.random() * (maxValue + 1));
		}
		return arr;
	}

	public static void main(String[] args) {
		int maxLen = 20;
		int maxValue = 50;
		int testTime = 3000000;
		System.out.println("如果没有错误信息打印，说明所有测试通过");
		System.out.println("测试开始");
		for (int i = 0; i < testTime; i++) {
			int[] arr = randomArray(maxLen, maxValue);
			int ans1 = maxSubArraySum1(arr);
			int ans2 = maxSubArraySum2(arr);
			int ans3 = maxSubArraySum3(arr);
			if (ans1 != ans2 || ans1 != ans3) {
				System.out.println("出错了！");
				System.out.println(ans1 + " , " + ans2 + " , " + ans3);
				break;
			}
		}
		System.out.println("测试结束");
	}

}
