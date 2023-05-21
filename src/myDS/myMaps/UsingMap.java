package myDS.myMaps;

import myDS.myCollections.MyEntry;

public class UsingMap {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SortedArrayMap<Integer, String> map1 = new SortedArrayMap<>();
		testMap(map1);
		
		UnSortedArrayMap<Integer, String> map2 = new UnSortedArrayMap<>();
		testMap(map2);
		
		System.out.println("Skip List");
		SkipListMap<Integer, String> map3 = new SkipListMap<>();
		testMap(map3);
		
		System.out.println("HashMap");
		HashBucketMap<Integer, String> map = new HashBucketMap<>();
		testMap(map);
		
		
		//testing my set
		testSet();
		
		//testing my multiset
		testMultiSet();
		
		//testing multimap
		testMultiMap();

	}
	
	public static void testMap(MyMap<Integer, String> map) {
	    
	    // Test put method
	    map.put(5, "Value 5");
	    map.put(3, "Value 3");
	    map.put(7, "Value 7");
	    map.put(2, "Value 2");
	    map.put(4, "Value 4");
	    map.put(6, "Value 6");
	    map.put(8, "Value 8");
	    
	    for(MyEntry<Integer, String> i: map.entrySet()) System.out.println(i);
	    
	    // Test get method
	    System.out.println("Value for key 4: " + map.get(4));
	    System.out.println("Value for key 9: " + map.get(9));

	    // Test remove method
	    map.remove(3);
	    System.out.println("Value for key 3 after removal: " + map.get(3));

	    // Test containsKey method
	    System.out.println("Contains key 6: " + map.get(6));
	    System.out.println("Contains key 10: " + map.get(10));
	    

	    // Test size and isEmpty methods
	    System.out.println("Size of the map: " + map.size());
	    System.out.println("Is the map empty? " + map.isEmpty());

	    // Test clear method
	    map.remove(4);
	    for(MyEntry<Integer, String> i: map.entrySet()) System.out.println(i);
	    System.out.println("Size of the map after clearing: " + map.size());
	    System.out.println("Is the map empty after clearing? " + map.isEmpty());
	}

	public static void testSet() {
	    SkipListSet<String> set = new SkipListSet<>();

	    // Test add method
	    set.add("Apple");
	    set.add("Banana");
	    set.add("Orange");
	    set.add("Apple"); // Adding a duplicate value

	    // Test contains method
	    System.out.println("Contains 'Banana': " + set.contains("Banana"));
	    System.out.println("Contains 'Grapes': " + set.contains("Grapes"));

	    // Test size and isEmpty methods
	    System.out.println("Size of the set: " + set.size());
	    System.out.println("Is the set empty? " + set.isEmpty());

	    // Test remove method
	    set.remove("Banana");
	    System.out.println("After removing 'Banana': " + set);
	    System.out.println("size: " + set.size());
	}

	public static void testMultiSet() {
	    SkipListMultiSet<String> multiSet = new SkipListMultiSet<>();

	    // Test add method
	    multiSet.add("Apple");
	    multiSet.add("Banana");
	    multiSet.add("Apple"); // Adding a duplicate value
	    multiSet.add("Orange");
	    multiSet.add("Banana");

	    // Test count method
	    System.out.println("Count of 'Apple': " + multiSet.count("Apple"));
	    System.out.println("Count of 'Banana': " + multiSet.count("Banana"));
	    System.out.println("Count of 'Grapes': " + multiSet.count("Grapes"));

	    // Test size and isEmpty methods
	    System.out.println("Size of the multiset: " + multiSet.size());
	    System.out.println("Is the multiset empty? " + multiSet.isEmpty());

	    // Test remove method
	    multiSet.remove("Apple");
	    System.out.println("After removing 'Apple': " + multiSet);
	    System.out.println("size: " + multiSet.size());
	    
	}
	
	public static void testMultiMap() {
	    HashMultiMap<String, Integer> multiMap = new HashMultiMap<>();

	    // Test put method
	    multiMap.put("Apple", 1);
	    multiMap.put("Banana", 2);
	    multiMap.put("Apple", 3);
	    multiMap.put("Orange", 4);
	    multiMap.put("Banana", 5);
	    for(MyEntry<String, Integer> i: multiMap.entrySet()) System.out.println(i);

	    
	    // Test get method
	    System.out.println("Values for key 'Apple': " + multiMap.get("Apple"));
	    System.out.println("Values for key 'Banana': " + multiMap.get("Banana"));
	    System.out.println("Values for key 'Grapes': " + multiMap.get("Grapes"));


	    // Test size and isEmpty methods
	    System.out.println("Size of the multimap: " + multiMap.size());
	    System.out.println("Is the multimap empty? " + multiMap.isEmpty());

	    // Test remove method
	    multiMap.removeAll("Apple");
	    for(MyEntry<String, Integer> i: multiMap.entrySet()) System.out.println(i);

	    // Test clear method
	    multiMap.remove("Banana", 5);
	    System.out.println("Is the multimap empty after clearing? " + multiMap.isEmpty());
	
	}


	
}
