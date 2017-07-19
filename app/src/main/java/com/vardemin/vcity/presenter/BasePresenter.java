package com.vardemin.vcity.presenter;

public interface BasePresenter<T> {
    void onAttach(T view);
    void onDetach();
    boolean isViewAttached();
}