package com.example;

import java.util.*;
import com.example.*;

public class Sort {
	private int[] array;

	public Sort(SortWrapper wrapper){
		this.array = wrapper.getArray();
	}

	public int[] sortArray(){
		Arrays.sort(this.array);		
		return this.array;
	}
}
