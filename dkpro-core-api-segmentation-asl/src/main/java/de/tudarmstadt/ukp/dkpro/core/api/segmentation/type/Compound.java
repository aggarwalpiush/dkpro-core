

   
/* Apache UIMA v3 - First created by JCasGen Sun Jan 28 11:38:37 CET 2018 */

package de.tudarmstadt.ukp.dkpro.core.api.segmentation.type;

import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandle;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.impl.TypeSystemImpl;
import org.apache.uima.fit.util.FSCollectionFactory;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.FSArray;
import org.apache.uima.jcas.tcas.Annotation;


/** This type represents a decompounding word, i.e.: flowerpot. Each Compound one have at least two Splits.
 * Updated by JCasGen Sun Jan 28 11:38:37 CET 2018
 * XML source: /Users/bluefire/git/dkpro-core/dkpro-core-api-segmentation-asl/src/main/resources/desc/type/LexicalUnits_customized.xml
 * @generated */
public class Compound extends Annotation {
 
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static String _TypeName = "de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Compound";
  
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(Compound.class);
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int type = typeIndexID;
  /** @generated
   * @return index of the type  
   */
  @Override
  public              int getTypeIndexID() {return typeIndexID;}
 
 
  /* *******************
   *   Feature Offsets *
   * *******************/ 
   
  public final static String _FeatName_splits = "splits";


  /* Feature Adjusted Offsets */
  private final static CallSite _FC_splits = TypeSystemImpl.createCallSite(Compound.class, "splits");
  private final static MethodHandle _FH_splits = _FC_splits.dynamicInvoker();

   
  /** Never called.  Disable default constructor
   * @generated */
  protected Compound() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param casImpl the CAS this Feature Structure belongs to
   * @param type the type of this Feature Structure 
   */
  public Compound(TypeImpl type, CASImpl casImpl) {
    super(type, casImpl);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public Compound(JCas jcas) {
    super(jcas);
    readObject();   
  } 


  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public Compound(JCas jcas, int begin, int end) {
    super(jcas);
    setBegin(begin);
    setEnd(end);
    readObject();
  }   

  /** 
   * <!-- begin-user-doc -->
   * Write your own initialization here
   * <!-- end-user-doc -->
   *
   * @generated modifiable 
   */
  private void readObject() {/*default - does nothing empty block */}
     
 
    
  //*--------------*
  //* Feature: splits

  /** getter for splits - gets A word that can be decomposed into different parts.
   * @generated
   * @return value of the feature 
   */
  public FSArray getSplits() { return (FSArray)(_getFeatureValueNc(wrapGetIntCatchException(_FH_splits)));}
    
  /** setter for splits - sets A word that can be decomposed into different parts. 
   * @generated
   * @param v value to set into the feature 
   */
  public void setSplits(FSArray v) {
    _setFeatureValueNcWj(wrapGetIntCatchException(_FH_splits), v);
  }    
    
    
  /** indexed getter for splits - gets an indexed value - A word that can be decomposed into different parts.
   * @generated
   * @param i index in the array to get
   * @return value of the element at index i 
   */
  public Split getSplits(int i) {
     return (Split)(((FSArray)(_getFeatureValueNc(wrapGetIntCatchException(_FH_splits)))).get(i));} 

  /** indexed setter for splits - sets an indexed value - A word that can be decomposed into different parts.
   * @generated
   * @param i index in the array to set
   * @param v value to set into the array 
   */
  public void setSplits(int i, Split v) {
    ((FSArray)(_getFeatureValueNc(wrapGetIntCatchException(_FH_splits)))).set(i, v);
  }  


  /**
   * Enum for all possible split levels for decompounding
   */
  public enum CompoundSplitLevel
  {
      NONE, ALL, LOWEST, HIGHEST
  };

  /**
   * 
   * Returns the splits from each leave from the split tree, excluding the linking morphemes
   * @param splitLevel the split level.
   * 
   * @return An array with the splits from each leave from the split tree.
   */
  public Split[] getSplitsWithoutMorpheme(CompoundSplitLevel splitLevel)
  {
      List<Split> splits = getSplits(createSplitsFromFSArray(getSplits()), false, splitLevel);
      return splits.toArray(new Split[splits.size()]);
  }

  /**
   * 
   * Returns the splits from each leave from the split tree, including the linking morphemes
   * @param splitLevel the split level.
   * 
   * @return An array with the splits from each leave from the split tree.
   * 
   */

  public Split[] getSplitsWithMorpheme(CompoundSplitLevel splitLevel)
  {
      final List<Split> splits = getSplits(createSplitsFromFSArray(getSplits()), true, splitLevel);
      return splits.toArray(new Split[splits.size()]);
  }

  /**
   * 
   * Returns a list of the fragments present in the leaves from the split tree stored in the
   * splits array.
   * 
   * @param splits
   *            Array containing the split tree
   * @param includeMorpheme
   *            Indicates whether or not the linking morphemes should be included
   * @param splitLevel
   *            The level of leaves that should be returned
   * @return A list of all splits on a level
   */
  private List<Split> getSplits(final Split[] splits, final boolean includeMorpheme,
          CompoundSplitLevel splitLevel)
  {
      List<Split> splitList = new ArrayList<Split>();

      switch (splitLevel) {

      case ALL:
          for (Split split : splits) {
              if (includeMorpheme || !(split instanceof LinkingMorpheme)) {
                  splitList.add(split);
              }
              if (split.getSplits() != null) {
                  splitList.addAll(getSplits(createSplitsFromFSArray(split.getSplits()),
                          includeMorpheme, splitLevel));
              }
          }
          return splitList;

      case LOWEST:
          for (Split split : splits) {
              if ((includeMorpheme || !(split instanceof LinkingMorpheme))
                      && (split.getSplits() == null || split.getSplits().size() == 0)) {
                  splitList.add(split);
              }
              if (split.getSplits() != null) {
                  splitList.addAll(getSplits(createSplitsFromFSArray(split.getSplits()),
                          includeMorpheme, splitLevel));
              }

          }
          return splitList;

      case HIGHEST:
          for (Split split : splits) {
              if (includeMorpheme || !(split instanceof LinkingMorpheme)) {
                  splitList.add(split);
              }
          }
          return splitList;

      default:
          return splitList;
      }
  }

  /**
   * 
   * Create a Split[] array from a FSArray
   * 
   * @param splitsFSArray
   *            FSArray containing the splits
   * @return The array containing the splits from FSArray
   */
  private Split[] createSplitsFromFSArray(final FSArray splitsFSArray)
  {
      final Collection<Split> splitsCollection = FSCollectionFactory.create(splitsFSArray,
              Split.class);
      return splitsCollection.toArray(new Split[splitsCollection.size()]);
  }
}
