import x10.io.Console;
import x10.compiler.TailAsync;

class SimpleDelegatingFinish {
  public static def main(args:Rail[String]!) {
        finish{
            val p = here;
            @TailAsync(true)
            async(p.next()){
                    Console.OUT.println(here);
                    @TailAsync(false)
                    async(p){
                            Console.OUT.println(here);
                    }
            }
            @TailAsync(true)
            async(p.next().next()){
                    Console.OUT.println(here);
                    @TailAsync(false)
                    async(p){
                            Console.OUT.println(here);
                    }
            }
            @TailAsync(true)
            async(p.next()){
                    Console.OUT.println(here);
                    @TailAsync(false)
                    async(p){
                            Console.OUT.println(here);
                    }
            }
        }
        Console.OUT.println("Done");
  }
}


