class GetProp {
	public static void main(String[] args) {
		for (int i = 0; i < args.length; i++) {
			System.out.println(System.getProperty(args[i]));
		}
	}

}
