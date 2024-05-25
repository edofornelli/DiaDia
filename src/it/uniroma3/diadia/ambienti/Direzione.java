package it.uniroma3.diadia.ambienti;

public enum Direzione {
	nord(){
		@Override
		public Direzione direzioneOpposta() {
			return sud;
		}
		@Override
		public String toString() {
			return "nord";
		}
	}, est(){
		@Override
		public Direzione direzioneOpposta() {
			return ovest;
		}
		@Override
		public String toString() {
			return "est";
		}
	}, ovest(){
		@Override
		public Direzione direzioneOpposta() {
			return est;
		}
		@Override
		public String toString() {
			return "ovest";
		}
	}, sud(){
		@Override
		public Direzione direzioneOpposta() {
			return nord;
		}
		
		@Override
		public String toString() {
			return "sud";
		}
	};
	public abstract Direzione direzioneOpposta();
}
