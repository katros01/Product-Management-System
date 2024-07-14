package Product.Management.System.Product.Management.System.utils;

import Product.Management.System.Product.Management.System.models.Category;

public class TreeNode {
    private Category category;
    private TreeNode left;
    private TreeNode right;

    public TreeNode(Category category) {
        this.category = category;
        this.left = null;
        this.right = null;
    }

    public Category getCategory() {
        return category;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }
}
