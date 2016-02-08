package robot;

public class Field {
	
	public enum Slot { 
		
		ONE   (1), 
		TWO   (2), 
		THREE (3), 
		FOUR  (4), 
		FIVE  (5); 
	
		private final int intValue;
		
		Slot (int intValue) {
			this.intValue = intValue;
		}
		
		public static Slot toEnum(int intValue) {
			for (Slot slot: Slot.values()) {
				if (slot.intValue == intValue)  {
					return slot;
				}
			}
			System.out.println("Slot value (" + intValue + ") is not a valid slot");
			return null;
		}
	};
	
	public enum Defence {

		LOW_BAR         ("Low Bar"),
		RAMPARTS        ("Ramparts"),
		MOAT            ("Moat"),
		ROCK_WALL       ("Rock Wall"),
		ROUGH_TERRAIN   ("Rough Terrain"),
		PORTCULLIS      ("Portcullis"),
		CHEVAL_DE_FRISE ("Cheval de Frise"); 
	
		private final String stringValue;
		
		Defence (String stringValue) {
			this.stringValue = stringValue;
		}
		
		public static Defence toEnum(String stringValue) {
			for (Defence defence: Defence.values()) {
				if (defence.stringValue.equals(stringValue))  {
					return defence;
				}
			}
			System.out.println("Defence value (" + stringValue + ") is not a valid defence string");
			return null;
		}

	}
        

}
