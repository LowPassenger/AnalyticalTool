# Little file parsing tool

My own opinion for this test task: 
https://drive.google.com/file/d/1N9AiKzkf0rJgHWAcdRjQ23QmrUUQm-tm/view?usp=share_link

And some notes about the code:

1. For first data filtration I use regular expression. You can see the partition version of it in the utils/Regex.java class. It was divided for better representation. The complete version of it is:
^[CD][\s]{1}(((([1-9][0]?)|[\*]?))|((([1-9][0]?)|[\*]?)[\.]{1}([1-3]|\*?)|\*?))[\s]{1}((([1-9][0]?)|\*?)|((([1-9][0]?)|\*?)[\.]{1}(([1-9][\d]?)|\*?))|((((([1-9][0]?)|\*?)[\\.]{1}(([1-9][\d]?)|\*?)))[\\.]{1}([1-5]|\*?)|\*?))[\s]{1}[PN][\s]{1}((((((3[01]|[12][0-9]|0?[1-9])\.(1[012]|0?[1-9])\.((?:19|20)\d{2}))|(((3[01]|[12][0-9]|0?[1-9])\.(1[012]|0?[1-9])\.((?:19|20)\d{2}))-{1}((3[01]|[12][0-9]|0?[1-9])\.(1[012]|0?[1-9])\.((?:19|20)\d{2})))))[\-]{1}(((((3[01]|[12][0-9]|0?[1-9])\.(1[012]|0?[1-9])\.((?:19|20)\d{2}))|(((3[01]|[12][0-9]|0?[1-9])\.(1[012]|0?[1-9])\.((?:19|20)\d{2}))-{1}((3[01]|[12][0-9]|0?[1-9])\.(1[012]|0?[1-9])\.((?:19|20)\d{2})))))))|((((3[01]|[12][0-9]|0?[1-9])\.(1[012]|0?[1-9])\.((?:19|20)\d{2}))|(((3[01]|[12][0-9]|0?[1-9])\.(1[012]|0?[1-9])\.((?:19|20)\d{2}))-{1}((3[01]|[12][0-9]|0?[1-9])\.(1[012]|0?[1-9])\.((?:19|20)\d{2}))))))(?:$|[\s]{1}[\d]{1,4})$
The regular expression is used to exclude deliberately incorrect data, such as letter instead numbers or extra whitespaces.
2. The next step is data validation by service/Validator.java class
3. My own opinion, the better case to store this kind of data is the SQL database like MySQL. The sorting and the ordering is the work of the database too.  But we need the application tool, so let me introduce it)
4. All application parameters present in the relevant classes of the utils/constants package.
5. Every line after the parsing from the internal file analysis is presented by the array of Strings. Then lines sorting by the type (waiting timeline or query) and saving in relevant collections. The collection type for waiting timelines is ArrayList and for queries is LinkedHashMap. Why? For better performance, of course. Every line contains the strictly defined number of elements, so an array is the best solution. All waiting timelines arrays groups to the List, and the ArrayList with the 8...12 byte per element is better for LinkedList with 40 byte for element. The queries collection contains an array of Strings too and the number of search limit elements from the waiting timelines collection. The LinkedHashMap is used for queries ordering.
6. For the report creation uses iteration by the foreach loop the query collection. Each EntrySet gives the array with the search parameters data and the number of elements in the waiting timelines collection for filtering and calculatind.
7. The output data is written in the /reports/ directory. Every report file contains the timestamp in its name.
8. Also in the /logs directory creates a very useful app.log file. It contains so much additional information about reading, calculating and writing data.
