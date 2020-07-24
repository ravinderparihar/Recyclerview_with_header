package com.bhs.myapplication.library;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView.Adapter;

/**
 * Collection of helper methods to notify the adapter of changes in the section items.
 */
/* default */ interface SectionNotifier {

    /**
     * Helper method that receives position in relation to the section, calculates the relative
     * position in the adapter and calls {@link Adapter#notifyItemInserted}.
     *
     * @param position position of the item in the section
     */
    void notifyItemInserted(final int position);

    /**
     * Helper method that calculates the relative position of all items of this section in the
     * adapter and calls {@link Adapter#notifyItemRangeInserted}.
     */
    void notifyAllItemsInserted();

    /**
     * Helper method that receives position in relation to the section, calculates the relative
     * position in the adapter and calls {@link Adapter#notifyItemRangeInserted}.
     *
     * @param positionStart position of the first item that was inserted in the section
     * @param itemCount     number of items inserted in the section
     */
    void notifyItemRangeInserted(final int positionStart, final int itemCount);

    /**
     * Helper method that receives position in relation to the section, calculates the relative
     * position in the adapter and calls {@link Adapter#notifyItemRemoved}.
     *
     * @param position position of the item in the section
     */
    void notifyItemRemoved(final int position);

    /**
     * Helper method that receives position in relation to the section, calculates the relative
     * position in the adapter and calls {@link Adapter#notifyItemRangeRemoved}.
     *
     * @param positionStart previous position of the first item that was removed from the section
     * @param itemCount     number of items removed from the section
     */
    void notifyItemRangeRemoved(final int positionStart, final int itemCount);

    /**
     * Helper method that calculates the relative header position in the adapter and calls
     * {@link Adapter#notifyItemChanged}.
     */
    void notifyHeaderChanged();

    /**
     * Helper method that calculates the relative header position in the adapter and calls
     * {@link Adapter#notifyItemChanged}.
     *
     * @param payload optional parameter, use null to identify a "full" update
     */
    void notifyHeaderChanged(@Nullable final Object payload);

    /**
     * Helper method that calculates the relative footer position in the adapter and calls
     * {@link Adapter#notifyItemChanged}.
     */
    void notifyFooterChanged();

    /**
     * Helper method that calculates the relative footer position in the adapter and calls
     * {@link Adapter#notifyItemChanged}.
     *
     * @param payload optional parameter, use null to identify a "full" update
     */
    void notifyFooterChanged(@Nullable final Object payload);

    /**
     * Helper method that receives position in relation to the section, calculates the relative
     * position in the adapter and calls {@link Adapter#notifyItemChanged}.
     *
     * @param position position of the item in the section
     */
    void notifyItemChanged(final int position);

    /**
     * Helper method that receives position in relation to the section, calculates the relative
     * position in the adapter and calls {@link Adapter#notifyItemChanged}.
     *
     * @param position position of the item in the section
     * @param payload  optional parameter, use null to identify a "full" update
     */
    void notifyItemChanged(final int position, @Nullable final Object payload);

    /**
     * Helper method that receives position in relation to the section, calculates the relative
     * position in the adapter and calls {@link Adapter#notifyItemRangeChanged}.
     */
    void notifyAllItemsChanged();

    /**
     * Helper method that receives position in relation to the section, calculates the relative
     * position in the adapter and calls {@link Adapter#notifyItemRangeChanged}.
     *
     * @param payload  optional parameter, use null to identify a "full" update
     */
    void notifyAllItemsChanged(@Nullable final Object payload);

    /**
     * Helper method that receives position in relation to the section, calculates the relative
     * position in the adapter and calls {@link Adapter#notifyItemRangeChanged}.
     *
     * @param positionStart position of the first item that was changed in the section
     * @param itemCount     number of items changed in the section
     */
    void notifyItemRangeChanged(final int positionStart, final int itemCount);

    /**
     * Helper method that receives position in relation to the section, calculates the relative
     * position in the adapter and calls {@link Adapter#notifyItemRangeChanged}.
     *
     * @param positionStart position of the first item that was inserted in the section
     * @param itemCount     number of items inserted in the section
     * @param payload       optional parameter, use null to identify a "full" update
     */
    void notifyItemRangeChanged(final int positionStart, final int itemCount, @Nullable final Object payload);

    /**
     * Helper method that receives position in relation to the section, calculates the relative
     * position in the adapter and calls {@link Adapter#notifyItemMoved}.
     *
     * @param fromPosition previous position of the item in the section
     * @param toPosition   new position of the item in the section
     */
    void notifyItemMoved(final int fromPosition, final int toPosition);

    /**
     * Helper method that calls {@link Adapter#notifyItemChanged} with the position of the {@link Section.State}
     * view holder in the adapter. Useful to be called after changing the State from
     * LOADING/FAILED/EMPTY to LOADING/FAILED/EMPTY.
     *
     * @param previousState previous state of section
     */
    void notifyNotLoadedStateChanged(final Section.State previousState);


    void notifyStateChangedToLoaded(final Section.State previousState);


    void notifyStateChangedFromLoaded(final int previousContentItemsCount);

    void notifyHeaderInserted();

    void notifyFooterInserted();


    void notifyHeaderRemoved();


    void notifyFooterRemoved();


    void notifySectionChangedToVisible();


    void notifySectionChangedToInvisible(final int previousSectionPosition);
}
