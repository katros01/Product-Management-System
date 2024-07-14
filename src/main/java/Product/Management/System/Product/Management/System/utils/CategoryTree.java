package Product.Management.System.Product.Management.System.utils;

import Product.Management.System.Product.Management.System.models.Category;

public class CategoryTree {
    private TreeNode root;

    public CategoryTree() {
        this.root = null;
    }

    public void addCategory(Category category) {
        root = addRecursive(root, category);
    }

    private TreeNode addRecursive(TreeNode current, Category category) {
        if (current == null) {
            return new TreeNode(category);
        }

        if (category.getName().compareTo(current.getCategory().getName()) < 0) {
            current.setLeft(addRecursive(current.getLeft(), category));
        } else if (category.getName().compareTo(current.getCategory().getName()) > 0) {
            current.setRight(addRecursive(current.getRight(), category));
        } else {

            return current;
        }

        return current;
    }

    public void deleteCategory(String name) {
        root = deleteRecursive(root, name);
    }

    private TreeNode deleteRecursive(TreeNode current, String name) {
        if (current == null) {
            return null;
        }

        if (name.equals(current.getCategory().getName())) {

            if (current.getLeft() == null && current.getRight() == null) {
                return null;
            }

            if (current.getRight() == null) {
                return current.getLeft();
            }

            if (current.getLeft() == null) {
                return current.getRight();
            }

            String smallestValue = findSmallestValue(current.getRight());
            current.getCategory().setName(smallestValue);
            current.setRight(deleteRecursive(current.getRight(), smallestValue));
            return current;

        }

        if (name.compareTo(current.getCategory().getName()) < 0) {
            current.setLeft(deleteRecursive(current.getLeft(), name));
            return current;
        }

        current.setRight(deleteRecursive(current.getRight(), name));
        return current;
    }

    private String findSmallestValue(TreeNode root) {
        return root.getLeft() == null ? root.getCategory().getName() : findSmallestValue(root.getLeft());
    }

    public Category searchCategory(String name) {
        return searchRecursive(root, name);
    }

    private Category searchRecursive(TreeNode current, String name) {
        if (current == null) {
            return null;
        }

        if (name.equals(current.getCategory().getName())) {
            return current.getCategory();
        }

        return name.compareTo(current.getCategory().getName()) < 0
                ? searchRecursive(current.getLeft(), name)
                : searchRecursive(current.getRight(), name);
    }
}
