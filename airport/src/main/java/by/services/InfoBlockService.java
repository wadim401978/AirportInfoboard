package by.services;

import by.dao.model.infomsg.InfoBlock;
import java.util.List;

public interface InfoBlockService<T extends InfoBlock> extends ActiveEntityService<T> {
	public List<T> getActiveBlocks();
	default public T getNextActiveBlock(int id) {
		T block = null;
		List<T> activeList = getActiveBlocks();
		if(activeList.isEmpty()==false) {
			for(T item : activeList) {
				if(item.getId() > id) {
					block = item;
					break;
				}	
			}
			if(block==null) {
				block = activeList.get(0);
			}
		}
		return block;
	}
}
