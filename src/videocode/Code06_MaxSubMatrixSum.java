package videocode;

public class Code06_MaxSubMatrixSum {

	// 非常好想
	// 面试这么写一定没分的方法
	public static int maxSubMatrixSum1(int[][] m) {
		if (m == null || m.length == 0 || m[0] == null || m[0].length == 0) {
			return 0;
		}
		int M = m.length;
		int N = m[0].length;
		int ans = Integer.MIN_VALUE;
		for (int lur = 0; lur < M; lur++) {
			for (int luc = 0; luc < N; luc++) {
				// (lur, luc)
				for (int rdr = lur; rdr < M; rdr++) {
					for (int rdc = luc; rdc < N; rdc++) {
						// (rdr, rdc)
						int sum = 0;
						for (int i = lur; i <= rdr; i++) {
							for (int j = luc; j <= rdc; j++) {
								sum += m[i][j];
							}
						}
						ans = Math.max(ans, sum);
					}
				}
			}
		}
		return ans;
	}

	public static int maxSubMatrixSum2(int[][] m) {
		if (m == null || m.length == 0 || m[0] == null || m[0].length == 0) {
			return 0;
		}
		int M = m.length;
		int N = m[0].length;
		int ans = Integer.MIN_VALUE;
		for (int start = 0; start < M; start++) { 
			int[] arr = new int[N]; // 0 ,0,0,0,..
			for (int cur = start; cur < M; cur++) {		
				for (int i = 0; i < N; i++) {
					arr[i] += m[cur][i];
				}
				ans = Math.max(ans, maxSubArraySum(arr));
			}
		}
		return ans;
	}

	public static int maxSubMatrixSum3(int[][] m) {
		if (m == null || m.length == 0 || m[0] == null || m[0].length == 0) {
			return 0;
		}
		m = m.length < m[0].length ? m : rotate(m);
		int M = m.length;
		int N = m[0].length;
		int ans = Integer.MIN_VALUE;
		for (int start = 0; start < M; start++) {
			int[] arr = new int[N];
			for (int cur = start; cur < M; cur++) {
				for (int i = 0; i < N; i++) {
					arr[i] += m[cur][i];
				}
				ans = Math.max(ans, maxSubArraySum(arr));
			}
		}
		return ans;
	}

	public static int[][] rotate(int[][] m) {
		int M = m.length;
		int N = m[0].length;
		int[][] ans = new int[N][M];
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				ans[j][i] = m[i][j];
			}
		}
		return ans;
	}

	// 求子数组的最大累加和！
	public static int maxSubArraySum(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		int N = arr.length;
		int pre = arr[0];
		int ans = arr[0];
		for (int i = 1; i < N; i++) {
			pre = Math.max(arr[i], arr[i] + pre);
			ans = Math.max(ans, pre);
		}
		return ans;
	}

	public static int[][] randomMatrix(int maxLen, int maxValue) {
		int rowSize = (int) (Math.random() * (maxLen + 1));
		int colSize = (int) (Math.random() * (maxLen + 1));
		int[][] m = new int[rowSize][colSize];
		for (int i = 0; i < rowSize; i++) {
			for (int j = 0; j < colSize; j++) {
				m[i][j] = (int) (Math.random() * (maxValue + 1)) - (int) (Math.random() * (maxValue + 1));
			}
		}
		return m;
	}

	public static void main(String[] args) {
		int maxLen = 10;
		int maxValue = 30;
		int testTime = 300000;
		System.out.println("功能测试开始");
		boolean pass = true;
		for (int i = 0; i < testTime; i++) {
			int[][] m = randomMatrix(maxLen, maxValue);
			int ans1 = maxSubMatrixSum1(m);
			int ans2 = maxSubMatrixSum2(m);
			int ans3 = maxSubMatrixSum3(m);
			if (ans1 != ans2 || ans1 != ans3) {
				System.out.println("出错了！");
				System.out.println(ans1 + " , " + ans2 + " , " + ans3);
				pass = false;
				break;
			}
		}
		System.out.println("是否所有比对都一致 : " + (pass ? "是" : "否"));
		System.out.println("功能测试结束");
		System.out.println("==================");

		System.out.println("性能测试开始");
		int rowSize = 10000;
		int colSize = 100;
		int[][] m = new int[rowSize][colSize];
		for (int i = 0; i < rowSize; i++) {
			for (int j = 0; j < colSize; j++) {
				m[i][j] = (int) (Math.random() * (maxValue + 1)) - (int) (Math.random() * (maxValue + 1));
			}
		}

		long start;
		long end;
		start = System.currentTimeMillis();
		int ans2 = maxSubMatrixSum2(m);
		end = System.currentTimeMillis();
		System.out.println("方法2的运行结果 : " + ans2 + " , 运行时间 : " + (end - start) + " ms");

		start = System.currentTimeMillis();
		int ans3 = maxSubMatrixSum3(m);
		end = System.currentTimeMillis();
		System.out.println("方法3的运行结果 : " + ans3 + " , 运行时间 : " + (end - start) + " ms");
		System.out.println("性能测试结束");
		System.out.println("==================");

	}

}
