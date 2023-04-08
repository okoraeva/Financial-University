import java.util.*;

class Main
{
    public static void main(String[] args)
    {
        List<String> listWithDuplicates = new ArrayList<>(Arrays.asList("a", "b", "a", "c"));

        List<String> listWithoutDuplicates = new ArrayList<>();
        Set<String> uniqueValues = new HashSet<>();

        for (String listWithDuplicate : listWithDuplicates) {
            if (uniqueValues.add(listWithDuplicate)) {
                listWithoutDuplicates.add(listWithDuplicate);
            }
        }

        System.out.println(listWithoutDuplicates);
    }
}