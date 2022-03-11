package hotciv.framework.strategies;

/** Represents an age with a companion aging algorithm.
 *
 * Responsibilities:
 *  1) Advancing the stored age.
 *
 */
public interface WorldAgingStrategy {

  /**
   * Advance the stored age
   */
  int advanceAge(int age);
}