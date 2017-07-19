package com.vardemin.vcity.contract;

public interface MVPContract {

    interface View {
        /**
         * Show Loading by state
         * @param state isLoading?
         */
        void showLoading(boolean state);

        /**
         * Call on error occurs
         * @param err to display
         */
        void showError(String err);
    }

    interface Presenter<V extends View> {
        /**
         * Get attached view
         * @return view
         */
        V getView();

        /**
         * Cal on attach view
         * @param view to attach
         */
        void attachView(V view);

        /**
         * Call to detach view
         */
        void detachView();

        /**
         * Is view null ?
         * @return if not null
         */
        boolean isViewAlive();
    }
}
