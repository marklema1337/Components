package com.lbs.data.database;

public interface IMultiCompany {
  public static final String MC_SUBCOMPANY_COLUMN_NAME = "TE_SUBCOMPANY";
  
  public static final String MC_INHTYPE_COLUMN_NAME = "TE_INHTYPE";
  
  public static final String MC_ORGIGINALREF_COLUMN_NAME = "TE_ORIGINALREF";
  
  public static final String MC_COPYBITLIST_COLUMN_NAME = "TE_COPYBITLIST";
  
  public static final String MC_SOURCEREF_COLUMN_NAME = "TE_SOURCEREF";
  
  public static final String MC_DESTREF_COLUMN_NAME = "TE_DESTREF";
  
  public static final String MC_SOURCECOMP_COLUMN_NAME = "TE_SOURCECOMPANY";
  
  public static final String MC_DESTCOMP_COLUMN_NAME = "TE_DESTCOMPANY";
  
  public static final String MC_SUBCOMPANY_PROP_NAME = "SubCompany";
  
  public static final String MC_INHTYPE_PROP_NAME = "InheritanceType";
  
  public static final String MC_ORGIGINALREF_PROP_NAME = "OriginalLogicalRef";
  
  public static final String MC_COPYBITLIST_PROP_NAME = "RecordCopyBitList";
  
  public static final String MC_SOURCEREF_PROP_NAME = "SourceCrossRef";
  
  public static final String MC_DESTREF_PROP_NAME = "DestinationCrossRef";
  
  public static final String MC_SOURCECOMP_PROP_NAME = "SourceCompany";
  
  public static final String MC_DESTCOMP_PROP_NAME = "DestinationCompany";
  
  public static final String MC_SUBCOMPANY_PARAM_NAME = "PINT_SUBCOMPANY";
  
  public static final String MC_INHTYPE_PARAM_NAME = "PINT_INHTYPE";
  
  public static final String MC_ORGIGINALREF_PARAM_NAME = "PINT_ORIGINALREF";
  
  public static final String MC_COPYBITLIST_PARAM_NAME = "PINT_COPYBITLIST";
  
  public static final String MC_BITMASK_PARAM_NAME = "PINT_BITMASK";
  
  public static final String MC_SUBCOMPANY_N_TERM_NAME = "TINT_SUBCOMPANY";
  
  public static final String MC_SUBCOMPANY_0_TERM_NAME = "TINT_UNTERNEHMEN";
  
  public static final String MC_INHTYPE_TERM_NAME = "TINT_INHTYPE";
  
  public static final String MC_ORGIGINALREF_TERM_NAME = "TINT_ORIGINALREF";
  
  public static final String MC_COPYBITLIST_TERM_NAME = "TINT_COPYBITLIST";
  
  public static final String MC_VAR_BITMASK_NAME = "VINT_MASK";
  
  public static final String MC_TERM_REPL_SUBCOMPANY_N = "((((TE_COPYBITLIST & $P(PINT_BITMASK))=0) AND (TE_SUBCOMPANY=0)) OR (TE_SUBCOMPANY=$P(PINT_SUBCOMPANY)))";
  
  public static final String MC_TERM_REPL_SUBCOMPANY_0 = "(TE_SUBCOMPANY=$P(PINT_SUBCOMPANY))";
  
  public static final String MC_TERM_TRANS_SUBCOMPANY_N = "((TE_SUBCOMPANY & $P(PINT_SUBCOMPANY) = $P(PINT_SUBCOMPANY)) OR (TE_SUBCOMPANY = 0))";
  
  public static final int OPTION_NONE = 0;
  
  public static final int OPTION_SUBCOMPANY = 1;
  
  public static final int OPTION_ALL = 2;
  
  public static final boolean MULTI_COMPANY_ENABLED = false;
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\database\IMultiCompany.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */