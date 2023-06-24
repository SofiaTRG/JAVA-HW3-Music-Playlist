/**
 * Represents an iterable collection of songs with ordering capabilities.
 */
public interface OrderedSongIterable extends Iterable<Song> {

    /**
     * Sets the scanning order of the songs in the collection.
     * @param order the scanning order to set
     */
    public void setScanningOrder(ScanningOrder order);
}
