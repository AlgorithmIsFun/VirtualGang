
public class View {
	private Mine_Model model;
	private Grid_Panel grid;
	private Header_Panel header;
	public View(Mine_Model model) {
		// TODO Auto-generated constructor stub
		this.model = model;
	}
	void create_Grid() {}
	
	public View(Mine_Model model) {
		this.model = model;
	}
	
	public Grid_Panel getGridPanel() {
		return grid;
	}
	
	public Header_Panel getHeaderPanel() {
		return header;
	}
	
}
