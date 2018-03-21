
public class Tuple{
		public final int x, y;
		public Tuple(int x, int y){
			this.x = x;
			this.y = y;
		}
		
		public int hashCode(){
			int hashCode = x;
			hashCode = 127*hashCode + y;
			return hashCode;
		}

		public boolean equals(Object obj){
			return (obj instanceof Tuple) && (obj.hashCode() == this.hashCode());
		}
		public String toString(){
			return x+" "+y;
		}
}

