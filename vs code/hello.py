# List to sort
numbers = [4, 2, 9, 1, 5, 6]

# Using sorted() function (creates a new sorted list)
sorted_numbers = sorted(numbers)

# Using sort() method (modifies the original list)
numbers.sort()

# Sorting in descending order using sorted() and sort()
numbers_desc = sorted(numbers, reverse=True)
sorted_numbers_desc = sorted(numbers, reverse=True)

# Printing results
print("Original list:", [4, 2, 9, 1, 5, 6])
print("Sorted list using sorted():", sorted_numbers)
print("Sorted list using sort() method:", numbers)
print("Sorted in descending order using sorted():", sorted_numbers_desc)
print("Sorted in descending order using sort() method:", numbers_desc)
