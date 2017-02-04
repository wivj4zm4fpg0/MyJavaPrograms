package ex14;

public class BinarySearchTree {
    private class BTNode {
        Integer data;
        BTNode left;
        BTNode right;

        BTNode(Integer data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }

        public void printNode() {
            printNode("");
        }

        public void printNode(String prefix) {
            if (right != null) {
                right.printNode(prefix + "\t");
            }
            System.out.println(prefix + this.data);
            if (left != null) {
                left.printNode(prefix + "\t");
            }
        }
    }

    private BTNode root;

    private BinarySearchTree() {
        root = null;
    }

    public BTNode search(Integer key) {
        // keyで示されるノードを探索する
        // keyが見つかればそのノードを返し，
        // 見つからなければnullを返す
        BTNode node = root;
        while (node != null) {
            int result = key.compareTo(node.data);
            if (result == 0) {
                return node;
            } else if (result < 0) {
                node = node.left;
            } else {
                node = node.right;
            }
        }
        return null;
    }

    public BTNode add(Integer key) {
        // keyを二分探索木に追加する
        // ここを作成する
        // どこに追加するか
        // 左部分木に追加するか
        // 右部分木に追加するか
        if (root == null) {
            root = new BTNode(key);
            return null;
        }
        BTNode node = root;
        while (node != null) {
            int result = key.compareTo(node.data);
            if (result == 0) {
                break;
            } else if (result < 0) {
                if (node.left != null) {
                    node = node.left;
                } else {
                    node.left = new BTNode(key);
                }
            } else {
                if (node.right != null) {
                    node = node.right;
                } else {
                    node.right = new BTNode(key);
                }
            }
        }
        return null;
    }

    public boolean remove(Integer key) {
        // keyを二分探索木から探し，あれば削除する
        // ここを作成する
        // 1. 子を持たない場合
        // 2. 子を1つだけ持つ場合
        // 3. 左右の子を持つ場合（右部分木で最小のノードで置き換える）
        BTNode node = root;
        BTNode parent = null;
        boolean isLeftChild = false;
        while (node != null) {
            int result = key.compareTo(node.data);
            if (result == 0) {
                if (node.left == null && node.right == null) {
                    if (parent == null) {
                        root = null;
                    } else if (isLeftChild) {
                        parent.left = null;
                    } else {
                        parent.right = null;
                    }
                } else if (node.left == null) {
                    if (parent == null) {
                        root = node.right;
                    } else if (isLeftChild) {
                        parent.left = node.right;
                    } else {
                        parent.right = node.right;
                    }
                } else if (node.right == null) {
                    if (parent == null) {
                        root = node.left;
                    } else if (isLeftChild) {
                        parent.left = node.left;
                    } else {
                        parent.right = node.left;
                    }
                } else {
                    BTNode x = removeMin(node, node.right);
                    if (parent == null) {
                        root = x;
                    } else if (isLeftChild) {
                        parent.left = x;
                    } else {
                        parent.right = x;
                    }
                    x.right = node.right;
                    x.left = node.left;
                }
                return true;
            } else if (result < 0) {
                parent = node;
                isLeftChild = true;
                node = node.left;
            } else {
                parent = node;
                isLeftChild = false;
                node = node.right;
            }
        }
        return false;
    }

    private BTNode removeMin(BTNode parent, BTNode p) {
        // 削除すべきノードが左右の子を持っているときに
        // 最小のノードを探し出すメソッド
        // ここを作成する
        boolean isLeft = false;
        while (p.left != null) {
            parent = p;
            isLeft = true;
            p = p.left;
        }
        if (isLeft) {
            parent.left = p.right;
        } else {
            parent.right = p.right;
        }
        return p;
    }

    public void printNode() {
        root.printNode("");
    }

    private void min() {
        BTNode node = root;
        while (node.left != null) {
            node = node.left;
        }
        System.out.println("最小の要素:" + node.data);
    }

    private void max() {
        BTNode node = root;
        while (node.right != null) {
            node = node.right;
        }
        System.out.println("最大の要素:" + node.data);
    }

    private void traverse() {
        BTNode node = root;
        traverse(node);
        System.out.println();
    }

    private void traverse(BTNode node) {
        if (node.left != null) {
            traverse(node.left);
        }
        System.out.print(node.data + "->");
        if (node.right != null) {
            traverse(node.right);
        }
    }

    public static void main(String[] args) {
        System.out.println("二分探索木の作成");
        BinarySearchTree bst = new BinarySearchTree();

        bst.add(13);
        bst.add(5);
        bst.add(21);
        bst.add(15);
        bst.add(7);
        bst.add(2);
        bst.add(6);

        bst.printNode();
        bst.min();
        bst.max();
        bst.traverse();

        BTNode result;
        System.out.print("ノード3を探索:");
        result = bst.search(3);
        if (result != null) {
            System.out.println(result.data);
        } else {
            System.out.println("探索失敗");
        }

        System.out.print("ノード5を探索:");
        result = bst.search(5);
        if (result != null) {
            System.out.println(result.data);
        } else {
            System.out.println("探索失敗");
        }

        System.out.println("ノード8を追加");
        bst.add(8);
        bst.printNode();
        bst.min();
        bst.max();
        bst.traverse();


        System.out.println("子を1つだけ持ったノードの削除");
        BinarySearchTree bst2 = new BinarySearchTree();

        bst2.add(9);
        bst2.add(14);
        bst2.add(5);
        bst2.add(3);
        bst2.add(4);
        bst2.add(1);
        bst2.add(14);
        bst2.printNode();
        bst2.min();
        bst2.max();
        bst2.traverse();

        System.out.println("ノード5を削除");
        if (bst2.remove(5)) {
            System.out.println("削除成功");
        }
        bst2.printNode();
        bst2.min();
        bst2.max();
        bst2.traverse();


        System.out.println("子を2つ持ったノードの削除");
        BinarySearchTree bst3 = new BinarySearchTree();

        bst3.add(20);
        bst3.add(23);
        bst3.add(29);
        bst3.add(7);
        bst3.add(18);
        bst3.add(4);
        bst3.add(2);
        bst3.add(5);
        bst3.add(10);
        bst3.add(15);
        bst3.printNode();
        bst3.min();
        bst3.max();
        bst3.traverse();

        System.out.println("ノード7を削除");
        if (bst3.remove(7)) {
            System.out.println("削除成功");
        }
        bst3.printNode();
        bst3.min();
        bst3.max();
        bst3.traverse();
    }
}
