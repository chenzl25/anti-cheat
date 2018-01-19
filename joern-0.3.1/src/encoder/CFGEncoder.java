package encoder;

import graphutils.PostorderIterator;
import cfg.*;
import cfg.nodes.*;
import java.util.Map;
import java.util.HashMap;

public class CFGEncoder {

	private Integer totalNodeCount = 0;

	private void init() {
		totalNodeCount = 0;
	}

	private void encodeImpl(CFG inputCFG) {
		PostorderIterator<CFGNode, CFGEdge> postorderIterator 
			= new PostorderIterator<CFGNode, CFGEdge>(inputCFG, inputCFG.getEntryNode());
		while (postorderIterator.hasNext()) {
			CFGNode node = postorderIterator.next();
			totalNodeCount++;
		}
	}

	public Map encodeToMap(CFG inputCFG) {
		init();
		encodeImpl(inputCFG);
		return prettyMap();
	}

	public String encodeToString(CFG inputCFG) {
		init();
		encodeImpl(inputCFG);
		return prettyString();
	}

	private Map prettyMap() {
		HashMap<String, Integer> m = new HashMap<String, Integer>();
		m.put("totalNodeCount", totalNodeCount);
		return m;
	}

	private String prettyString() {
		StringBuilder s = new StringBuilder();
		s.append("<CFGEncoder>\n");
		s.append("  totalNodeCount = " + totalNodeCount.toString() + '\n');
		s.append("</CFGEncoder>");
		return s.toString();
	}
}
