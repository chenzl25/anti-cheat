package encoder;

import graphutils.PostorderIterator;
import cfg.*;
import cfg.nodes.*;
import cdg.CDG;
import cdg.CDGEdge;
import java.util.Map;
import java.util.HashMap;

import java.util.Iterator;

import java.util.Set;
import java.util.HashSet;

public class CDGEncoder {

	private Integer totalNodeCount = 0;
	private Integer totalEdgeCount = 0;
	private Integer outDegree0NodeCount = 0;
	private Integer outDegree1NodeCount = 0;
	private Integer outDegree2NodeCount = 0;
	private Integer outDegree3NodeCount = 0;
	private Integer outDegree4NodeCount = 0;
	private Integer outDegree5NodeCount = 0;
	
	private void init() {
		totalNodeCount = 0;
		totalEdgeCount = 0;
		outDegree0NodeCount = 0;
		outDegree1NodeCount = 0;
		outDegree2NodeCount = 0;
		outDegree3NodeCount = 0;
		outDegree4NodeCount = 0;
		outDegree5NodeCount = 0;
	}

	private void encodeImpl(CDG inputCDG) {
		totalNodeCount = inputCDG.size();
		totalEdgeCount = inputCDG.numberOfEdges();
		// PostorderIterator<CFGNode, CDGEdge> pit 
		// 	= new PostorderIterator<CFGNode, CDGEdge>(inputCDG, inputCDG.getDominatorTree().getStartNode());
		Iterator<CFGNode> it = inputCDG.iterator();
		while (it.hasNext()) {
			CFGNode node = it.next();
			switch (inputCDG.outDegree(node)) {
				case 0:
					outDegree0NodeCount++;
					break;
				case 1:
					outDegree1NodeCount++;
					break;
				case 2:
					outDegree2NodeCount++;
					break;
				case 3:
					outDegree3NodeCount++;
					break;
				case 4:
					outDegree4NodeCount++;
					break;
				default:
					outDegree5NodeCount++;
			}
		}
	}

	public Map encodeToMap(CDG inputCDG) {
		init();
		encodeImpl(inputCDG);
		return prettyMap();
	}

	public String encodeToString(CDG inputCDG) {
		init();
		encodeImpl(inputCDG);
		return prettyString();
	}

	private Map prettyMap() {
		HashMap<String, Integer> m = new HashMap<String, Integer>();
		m.put("totalNodeCount", totalNodeCount);
		m.put("totalEdgeCount", totalNodeCount);
		m.put("outDegree0NodeCount", outDegree0NodeCount);
		m.put("outDegree1NodeCount", outDegree1NodeCount);
		m.put("outDegree2NodeCount", outDegree2NodeCount);
		m.put("outDegree3NodeCount", outDegree3NodeCount);
		m.put("outDegree4NodeCount", outDegree4NodeCount);
		m.put("outDegree5NodeCount", outDegree5NodeCount);
		return m;
	}

	private String prettyString() {
		StringBuilder s = new StringBuilder();
		s.append("<CDGEncoder>\n");
		s.append("  totalNodeCount = " + totalNodeCount.toString() + '\n');
		s.append("  totalEdgeCount = " + totalEdgeCount.toString() + '\n');
		s.append("  outDegree0NodeCount = " + outDegree0NodeCount.toString() + '\n');
		s.append("  outDegree1NodeCount = " + outDegree1NodeCount.toString() + '\n');
		s.append("  outDegree2NodeCount = " + outDegree2NodeCount.toString() + '\n');
		s.append("  outDegree3NodeCount = " + outDegree3NodeCount.toString() + '\n');
		s.append("  outDegree4NodeCount = " + outDegree4NodeCount.toString() + '\n');
		s.append("  outDegree5NodeCount = " + outDegree5NodeCount.toString() + '\n');
		s.append("</CDGEncoder>");
		return s.toString();
	}
}
