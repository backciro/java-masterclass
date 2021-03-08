package it.polito.vm;

class ControlPanel {
	
		String bv_id;
		int qty;
		
		public ControlPanel() {
			this.bv_id = "";
			this.qty = 0;
		}
		
		void refill(String id, int plus) {
			
			if (this.bv_id == id )
				this.qty += plus;
			
			else if (this.bv_id == "") {
				this.bv_id = id;
				this.qty = plus;
			}
		}
		
		boolean idCompare(String id) {
			return (this.bv_id == id);
		}
		
		int availablity() {
			return this.qty;
		}
		
		boolean authorize() {
			
			if (this.qty > 0) {
				this.qty--;
				return true;
			}
			else
				return false;
		}
}