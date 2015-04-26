import java.util.ArrayList;
import java.util.List;

public class Page {

	private static int graph_id = 1;
	private int id;
	public List<Page> inlinks;
	public List<Page> outlinks;
	public int inlinkSize;
	public int outlinkSize;
	private float current_rank;
	private float pass_on;

	public Page() {
		this.inlinks = new ArrayList<Page>();
		this.outlinks = new ArrayList<Page>();
		this.inlinkSize = 0;
		this.outlinkSize = 0;
		this.current_rank = 1;
		this.id = graph_id;
		this.pass_on = 0;
		graph_id++;
	}

	public int getId() {
		return this.id;
	}

	public void addInlink(Page page) {
		this.inlinks.add(page);
		this.inlinkSize += 1;
	}

	public void addOutlink(Page page) {
		this.outlinks.add(page);
		this.outlinkSize += 1;
	}

	public float getPageRank() {
		return current_rank;
	}

	public void setPageRank(float newPageRank) {
		this.current_rank = newPageRank;
	}

	public float getPassOn() {
		return pass_on;
	}

	public void setPassOn() {
		if (outlinkSize == 0) {
			pass_on = current_rank;
		} else {
			pass_on = (float) current_rank / outlinkSize;
		}
	}

}
