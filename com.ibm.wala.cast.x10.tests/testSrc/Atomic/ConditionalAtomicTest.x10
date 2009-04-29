/**
 * Minimal test for conditional atomics.
 */
public class ConditionalAtomicTest {

	var value1: int = 0;
	var value2: int = 0;

	public def run(): boolean = {
		val c: Clock = Clock.make();
		async(this.location) clocked(c) {
			// this activity waits until value1 and
			// value2 are equal, then atomically makes
			// value1 two higher then value2
			while (true) {
				var temp: int;
				atomic temp = value1;
				if (temp >= 42) break;
				when (value1 == value2) { value1++; value2--; }
			}
		}
		async(this.location) clocked(c) {
			// this activity waits until value1 is
			// two higher than value2, then atomically raises
			// value2 to value1's level so they become equal
			while (true) {
				var temp: int;
				atomic temp = value2;
				if (temp >= 42) break;
				when (value1 == value2 + 2)
				{ value2 = value1; }
				or (value1 != value2+2 &&
						value1 != value2) //something went wrong
				{ value1 = value2 = 43; /* error */ };
			}
		}
		next; // wait until both activities end

		var temp: int;
		atomic temp = value1;
		return temp == 42;
	}

	public static def main(var args: Rail[String]): void = {
		new ConditionalAtomicTest().run();
	}
}
