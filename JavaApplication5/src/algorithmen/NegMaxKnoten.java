/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithmen;

/**
 *
 * @author bfh
 */

import java.util.ArrayList;
import java.util.List;

import spiel.SpielStatus;
import spiel.NimStatus;


public class NegMaxKnoten extends Knoten<Integer> {
	
	public NegMaxKnoten(SpielStatus state) {
		super(state);
	}

	@Override
	public Knoten<Integer> nextNode() {

		if (!this.getState().isTerminal()) {

				int val = Integer.MIN_VALUE;
				NegMaxKnoten negMaxNode = null;

				for (NegMaxKnoten node : this.getChildren())
				{
					int newVal = node.getValue();
					
					if (negMaxNode == null || val<newVal) {
						negMaxNode = node;
						val = newVal;
					}
				}
				
				return negMaxNode;
		}
		return null;
	}

	@Override
	public Integer getValue() {
		int val;
		if (this.getState().isTerminal())
		{
			val = this.getState().getValue();
			if (this.getState().isMaxPlayer())
				val=-val;
		} else {
			val = -this.nextNode().getValue();
		}	
		
		return val;
	}

	public List<NegMaxKnoten> getChildren() {
		List<NegMaxKnoten> list = new ArrayList<NegMaxKnoten>();
		
		List<SpielStatus> cache = this.getState().nextStates();
		for (SpielStatus game : cache)
		{
			NegMaxKnoten n = new NegMaxKnoten(game.clone());
			list.add(n);
		}
		
		return list;
	}

	@Override
	public boolean solve() {
		return -this.getValue()>0;
	}
	
	

}
