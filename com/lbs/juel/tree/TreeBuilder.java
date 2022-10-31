package com.lbs.juel.tree;

import java.io.Serializable;

public interface TreeBuilder extends Serializable {
  Tree build(String paramString) throws TreeBuilderException;
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\juel\tree\TreeBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */