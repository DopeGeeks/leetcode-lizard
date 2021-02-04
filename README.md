# leetcode-lizard

<img src="https://i.imgur.com/6YJG4x7.jpg" title="LeetCode Lizard" width=20% align="right">
The lizard will try its best to help you pass the interview.

## Prerequisite

Install [Maven](https://maven.apache.org/install.html)

## Usage

### Build the Project

`cd leetcode-lizard && mvn package`

### Run the Program

If you have defined your main function in your java class

```bash
java -cp target/leetcode-lizard-1.0-SNAPSHOT.jar \
daohuei.leetcodelizard.["LeetCode Solution ClassName, i.e. 'TwoSum'"]
```

### Test

a. Test all the test cases at once:

```bash
mvn test
```

b. Test with specific class:

```bash
mvn -Dtest=["Your test class, i.e. 'TwoSumTest'"] test
```

## Acknowledgement

-   [Maven](https://maven.apache.org/)
-   [LeetCode](https://leetcode.com/)
