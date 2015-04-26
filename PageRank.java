import java.util.ArrayList;
import java.util.List;

public class PageRank {
	
	//Simple numerical pagerank with teleport constant functionality. Test graphs from Wikipedia and class notes

	List<Page> pages;
	float teleport_prob;
	int graph_size;
	float teleport_const;

	public PageRank(float teleport_prob) {
		this.pages = new ArrayList<Page>();
		this.teleport_prob = teleport_prob;
	}
	
	public PageRank() {
		this(0);
	}
	
	public void reset() {
		if (graph_size <= 1) {
			return;
		}
		for(Page p: pages) {
			p.setPageRank((float) p.inlinkSize / (graph_size - 1));
		}
	}

	public void addPage(Page page) {
		pages.add(page);
		for (int i = 0; i < page.outlinkSize; i++) {
			int outlink_id = page.outlinks.get(i).getId();
			for (Page p : pages) {
				if (p.getId() == outlink_id) {
					p.addInlink(page);
				}
			}
		}
		for (int i = 0; i < page.inlinks.size(); i++) {
			int inlink_id = page.inlinks.get(i).getId();
			for (Page p : pages) {
				if (p.getId() == inlink_id) {
					p.addOutlink(page);
				}
			}
		}
		graph_size++;
		teleport_const = teleport_prob / graph_size;

	}

	public void addLink(Page origin, Page destination) {
		origin.addOutlink(destination);
		destination.addInlink(origin);
	}

	public void iterate() {
		for (Page p : pages) {
			p.setPassOn();
		}

		for (Page p : pages) {
			float newPageRank = 0;
			for (Page p1 : p.inlinks) {
				newPageRank += p1.getPassOn();
			}
			newPageRank *= (1 - teleport_prob);
			newPageRank += teleport_const;
			p.setPageRank(newPageRank);
		}
	}
	
	public static void test1() {
		PageRank test = new PageRank((float)0.15);
		Page a = new Page();
		Page b = new Page();
		Page c = new Page();
		Page d = new Page();
		Page e = new Page();
		Page f = new Page();
		Page one = new Page();
		Page two = new Page();
		Page three = new Page();
		Page four = new Page();
		Page five = new Page();

		test.addPage(a);
		test.addPage(b);
		test.addPage(c);
		test.addPage(d);
		test.addPage(e);
		test.addPage(f);
		test.addPage(one);
		test.addPage(two);
		test.addPage(three);
		test.addPage(four);
		test.addPage(five);
		
		test.addLink(b, c);
		test.addLink(c, b);
		test.addLink(d, a);
		test.addLink(d, b);
		test.addLink(e, b);
		test.addLink(e, d);
		test.addLink(e, f);
		test.addLink(f, b);
		test.addLink(f, e);
		
		test.addLink(one, b);
		test.addLink(two, b);
		test.addLink(three, b);
		test.addLink(one, e);
		test.addLink(two, e);
		test.addLink(three, e);
		test.addLink(four, e);
		test.addLink(five, e);

		test.reset();
		
		for (int i = 0; i < 80; i++) {
			test.iterate();
		}

		System.out.println(a.getPageRank());
		System.out.println(b.getPageRank());
		System.out.println(c.getPageRank());
		System.out.println(d.getPageRank());
		System.out.println(e.getPageRank());
		System.out.println(f.getPageRank());
		System.out.println(one.getPageRank());
		System.out.println(two.getPageRank());
		System.out.println(three.getPageRank());
		System.out.println(four.getPageRank());
		System.out.println(five.getPageRank());
	}

	public static void main(String[] args) {
		PageRank test = new PageRank((float)0.15);
		Page a = new Page();
		Page b = new Page();
		Page c = new Page();
		Page d = new Page();
		Page e = new Page();
		Page f = new Page();
		Page g = new Page();
		Page h = new Page();


		test.addPage(a);
		test.addPage(b);
		test.addPage(c);
		test.addPage(d);
		test.addPage(e);
		test.addPage(f);
		test.addPage(g);
		test.addPage(h);

		
		test.addLink(a, b);
		test.addLink(b, c);
		test.addLink(b, e);
		test.addLink(c, b);
		test.addLink(c, d);
		test.addLink(c, e);
		test.addLink(c, f);
		test.addLink(d, e);
		test.addLink(e, f);
		test.addLink(e, h);
		test.addLink(f, g);
		test.addLink(g, b);
		test.addLink(g, d);
		test.addLink(g, e);
		test.addLink(g, f);


		test.reset();
		
		test.iterate();
		System.out.println(a.getPageRank());
		System.out.println(b.getPageRank());
		System.out.println(c.getPageRank());
		System.out.println(d.getPageRank());
		System.out.println(e.getPageRank());
		System.out.println(f.getPageRank());
		System.out.println(g.getPageRank());
		System.out.println(h.getPageRank());

		
		test.iterate();
		System.out.println(a.getPageRank());
		System.out.println(b.getPageRank());
		System.out.println(c.getPageRank());
		System.out.println(d.getPageRank());
		System.out.println(e.getPageRank());
		System.out.println(f.getPageRank());
		System.out.println(g.getPageRank());
		System.out.println(h.getPageRank());

	}

}
