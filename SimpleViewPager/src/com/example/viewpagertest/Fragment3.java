package com.example.viewpagertest;import android.support.v4.app.Fragment;import android.os.Bundle;import android.view.LayoutInflater;import android.view.View;import android.view.ViewGroup; public class Fragment3 extends Fragment {	@Override	public View onCreateView(LayoutInflater inflater, ViewGroup container,			Bundle savedInstanceState) {		// TODO Auto-generated method stub		 ViewGroup rootView = (ViewGroup) inflater.inflate(	                R.layout.frame3, container, false);		return rootView;	}}