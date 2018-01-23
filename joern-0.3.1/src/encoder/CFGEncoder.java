package encoder;

import graphutils.PostorderIterator;
import cfg.*;
import cfg.nodes.*;
import java.util.Map;
import java.util.HashMap;

import java.util.Set;
import java.util.HashSet;

public class CFGEncoder {

	private Integer totalNodeCount = 0;
	private Integer totalEdgeCount = 0;
	private Integer inDegree0NodeCount = 0;
	private Integer inDegree1NodeCount = 0;
	private Integer inDegree2NodeCount = 0;
	private Integer inDegree3NodeCount = 0;
	private Integer inDegree4NodeCount = 0;
	private Integer inDegree5NodeCount = 0;
	private Integer outDegree0NodeCount = 0;
	private Integer outDegree1NodeCount = 0;
	private Integer outDegree2NodeCount = 0;
	private Integer outDegree3NodeCount = 0;
	private Integer outDegree4NodeCount = 0;
	private Integer outDegree5NodeCount = 0;
	
	private void init() {
		totalNodeCount = 0;
		totalEdgeCount = 0;
		inDegree0NodeCount = 0;
		inDegree1NodeCount = 0;
		inDegree2NodeCount = 0;
		inDegree3NodeCount = 0;
		inDegree4NodeCount = 0;
		inDegree5NodeCount = 0;
		outDegree0NodeCount = 0;
		outDegree1NodeCount = 0;
		outDegree2NodeCount = 0;
		outDegree3NodeCount = 0;
		outDegree4NodeCount = 0;
		outDegree5NodeCount = 0;
	}

	private void encodeImpl(CFG inputCFG) {

		// remove trival nodes from cfg
		Set<CFGNode> nodeSet = new HashSet<CFGNode>();
		Set<CFGNode> toRemoveSet = new HashSet<CFGNode>();
		PostorderIterator<CFGNode, CFGEdge> postorderIterator 
			= new PostorderIterator<CFGNode, CFGEdge>(inputCFG, inputCFG.getEntryNode());
		while (postorderIterator.hasNext()) {
			CFGNode itNode = postorderIterator.next();
			nodeSet.add(itNode);
		}
		int count = 0;
		for (CFGNode cfgNode : inputCFG.getVertices()) {
			if (!nodeSet.contains(cfgNode)) {
				toRemoveSet.add(cfgNode);
			}
		}
		for (CFGNode cfgNode : toRemoveSet) {
			if (cfgNode instanceof ASTNodeContainer) {
				ASTNodeContainer astNodeContainer = (ASTNodeContainer) cfgNode;
				astNodeContainer.getASTNode().unmarkAsCFGNode();
			}
			inputCFG.removeVertex(cfgNode);
		}

		totalNodeCount = inputCFG.size();
		totalEdgeCount = inputCFG.numberOfEdges();
		PostorderIterator<CFGNode, CFGEdge> pit 
			= new PostorderIterator<CFGNode, CFGEdge>(inputCFG, inputCFG.getEntryNode());
		while (pit.hasNext()) {
			CFGNode node = pit.next();
			switch (inputCFG.outDegree(node)) {
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
			switch (inputCFG.inDegree(node)) {
				case 0:
					inDegree0NodeCount++;
					break;
				case 1:
					inDegree1NodeCount++;
					break;
				case 2:
					inDegree2NodeCount++;
					break;
				case 3:
					inDegree3NodeCount++;
					break;
				case 4:
					inDegree4NodeCount++;
					break;
				default:
					inDegree5NodeCount++;
			}
			
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
		m.put("totalEdgeCount", totalNodeCount);
		m.put("inDegree0NodeCount", inDegree1NodeCount);
		m.put("inDegree1NodeCount", inDegree1NodeCount);
		m.put("inDegree2NodeCount", inDegree2NodeCount);
		m.put("inDegree3NodeCount", inDegree3NodeCount);
		m.put("inDegree4NodeCount", inDegree4NodeCount);
		m.put("inDegree5NodeCount", inDegree5NodeCount);
		m.put("outDegree0NodeCount", outDegree1NodeCount);
		m.put("outDegree1NodeCount", outDegree1NodeCount);
		m.put("outDegree2NodeCount", outDegree2NodeCount);
		m.put("outDegree3NodeCount", outDegree3NodeCount);
		m.put("outDegree4NodeCount", outDegree4NodeCount);
		m.put("outDegree5NodeCount", outDegree5NodeCount);
		return m;
	}

	private String prettyString() {
		StringBuilder s = new StringBuilder();
		s.append("<CFGEncoder>\n");
		s.append("  totalNodeCount = " + totalNodeCount.toString() + '\n');
		s.append("  totalEdgeCount = " + totalEdgeCount.toString() + '\n');
		s.append("  inDegree0NodeCount = " + inDegree1NodeCount.toString() + '\n');
		s.append("  inDegree1NodeCount = " + inDegree1NodeCount.toString() + '\n');
		s.append("  inDegree2NodeCount = " + inDegree2NodeCount.toString() + '\n');
		s.append("  inDegree3NodeCount = " + inDegree3NodeCount.toString() + '\n');
		s.append("  inDegree4NodeCount = " + inDegree4NodeCount.toString() + '\n');
		s.append("  inDegree5NodeCount = " + inDegree5NodeCount.toString() + '\n');
		s.append("  outDegree0NodeCount = " + outDegree1NodeCount.toString() + '\n');
		s.append("  outDegree1NodeCount = " + outDegree1NodeCount.toString() + '\n');
		s.append("  outDegree2NodeCount = " + outDegree2NodeCount.toString() + '\n');
		s.append("  outDegree3NodeCount = " + outDegree3NodeCount.toString() + '\n');
		s.append("  outDegree4NodeCount = " + outDegree4NodeCount.toString() + '\n');
		s.append("  outDegree5NodeCount = " + outDegree5NodeCount.toString() + '\n');
		s.append("</CFGEncoder>");
		return s.toString();
	}
}
