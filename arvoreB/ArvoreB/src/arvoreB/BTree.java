package arvoreB;

   public class BTree<T extends Comparable<T>> {
    NodeB<T> root;
    int ordem = 5;
    int totalElementos = 0;
    
    public NodeB<T> find(NodeB<T> x, T k) { //letra F
        int i = 0;
        while (i < x.getnChaves() && k.compareTo(x.getChaves()[i]) > 0) {
            i++;
        }
        if (i < x.getnChaves() && k.compareTo(x.getChaves()[i]) == 0) {
            return x;
        } else if (x.isLeaf()) {
            return null;
        } else {
            return find(x.getPonteiro()[i], k);
        }
    }

    public void insert(T k) { //letra A
        if (find(root, k) == null) {
            if (root.getnChaves() == ordem - 1) {
                NodeB<T> s = new NodeB<>(ordem, false);
                s.getPonteiro()[0] = root;
                root = s;
                splitChild(s, 0);
                insertNonFull(s, k);
            } else {
                insertNonFull(root, k);
            }
            totalElementos++;
        }
    }

    private void splitChild(NodeB<T> x, int i) {
        NodeB<T> y = x.getPonteiro()[i];
        NodeB<T> z = new NodeB<>(ordem, y.isLeaf());
        x.getChaves()[x.getnChaves()] = y.getChaves()[ordem / 2];
        x.setnChaves(x.getnChaves() + 1);
        for (int j = 0; j < ordem / 2; j++) {
            z.getChaves()[j] = y.getChaves()[j + ordem / 2];
        }
        if (!y.isLeaf()) {
            for (int j = 0; j <= ordem / 2; j++) {
                z.getPonteiro()[j] = y.getPonteiro()[j + ordem / 2];
            }
        }
        y.setnChaves(ordem / 2);
        for (int j = x.getnChaves(); j > i; j--) {
            x.getPonteiro()[j + 1] = x.getPonteiro()[j];
        }
        x.getPonteiro()[i + 1] = z;
        for (int j = x.getnChaves() - 1; j >= i; j--) {
            x.getChaves()[j + 1] = x.getChaves()[j];
        }
        x.getChaves()[i] = y.getChaves()[ordem / 2];
        x.setnChaves(x.getnChaves() + 1);
    }

    private void insertNonFull(NodeB<T> x, T k) {
        int i = x.getnChaves() - 1;
        if (x.isLeaf()) {
            while (i >= 0 && k.compareTo(x.getChaves()[i]) < 0) {
                x.getChaves()[i + 1] = x.getChaves()[i];
                i--;
            }
            i++;
            x.getChaves()[i] = k;
            x.setnChaves(x.getnChaves() + 1);
        } else {
            while (i >= 0 && k.compareTo(x.getChaves()[i]) < 0) {
                i--;
            }
            i++;
            if (x.getPonteiro()[i].getnChaves() == ordem - 1) {
                splitChild(x, i);
                if (k.compareTo(x.getChaves()[i]) > 0) {
                    i++;
                }
            }
            insertNonFull(x.getPonteiro()[i], k);
        }
    }
        public void remove(T value) { //letra B
            if (root == null) {
                return;
            }
            remove(root, value);
        }
    
        private void remove(NodeB<T> currentNode, T value) {
            if (currentNode == null) {
                return;
            }
    
            int index = findKeyIndex(currentNode, value);
    
            if (index < currentNode.getnChaves() && value.compareTo(currentNode.getChaves()[index]) == 0) {
                if (currentNode.isLeaf()) {
                    removeKey(currentNode, index);
                } else {
                    NodeB<T> successor = findMinValueNode(currentNode.getPonteiro()[index + 1]);
                    currentNode.setChaves(successor.getChaves()[0], index);
                    remove(currentNode.getPonteiro()[index + 1], successor.getChaves()[0]);
                }
            } else {
                if (currentNode.isLeaf()) {
                    return;
                }
                boolean isLastKey = (index == currentNode.getnChaves());
    
                if (currentNode.getPonteiro()[index].getnChaves() < ordem) {
                    fillChildNode(currentNode, index);
                }
    
                if (isLastKey && index > currentNode.getnChaves()) {
                    remove(currentNode.getPonteiro()[index - 1], value);
                } else {
                    remove(currentNode.getPonteiro()[index], value);
                }
            }
        }
    
        private int findKeyIndex(NodeB<T> node, T value) {
            int index = 0;
            while (index < node.getnChaves() && value.compareTo(node.getChaves()[index]) > 0) {
                index++;
            }
            return index;
        }
    
        private void removeKey(NodeB<T> node, int index) {
            for (int i = index; i < node.getnChaves() - 1; i++) {
                node.setChaves(node.getChaves()[i + 1], i);
            }
            node.setnChaves(node.getnChaves() - 1);
        }
    
        private void fillChildNode(NodeB<T> node, int index) {
            if (index != 0 && node.getPonteiro()[index - 1].getnChaves() >= ordem) {
                borrowFromPrevious(node, index);
            } else if (index != node.getnChaves() && node.getPonteiro()[index + 1].getnChaves() >= ordem) {
                borrowFromNext(node, index);
            } else {
                if (index != node.getnChaves()) {
                    mergeChildNodes(node, index);
                } else {
                    mergeChildNodes(node, index - 1);
                }
            }
        }
    
        private void borrowFromPrevious(NodeB<T> node, int index) {
            NodeB<T> child = node.getPonteiro()[index];
            NodeB<T> sibling = node.getPonteiro()[index - 1];
    
            for (int i = child.getnChaves() - 1; i >= 0; i--) {
                child.setChaves(child.getChaves()[i], i + 1);
            }
    
            if (!child.isLeaf()) {
                for (int i = child.getnChaves(); i >= 0; i--) {
                    child.setPonteiro(child.getPonteiro()[i], i + 1);
                }
            }
    
            child.setChaves(node.getChaves()[index - 1], 0);
    
            if (!node.isLeaf()) {
                child.setPonteiro(sibling.getPonteiro()[sibling.getnChaves()], 0);
            }
    
            node.setChaves(sibling.getChaves()[sibling.getnChaves() - 1], index - 1);
    
            child.setnChaves(child.getnChaves() + 1);
            sibling.setnChaves(sibling.getnChaves() - 1);
        }
    
        private void borrowFromNext(NodeB<T> node, int index) {
            NodeB<T> child = node.getPonteiro()[index];
            NodeB<T> sibling = node.getPonteiro()[index + 1];
    
            child.setChaves(node.getChaves()[index], child.getnChaves());
    
            if (!child.isLeaf()) {
                child.setPonteiro(sibling.getPonteiro()[0], child.getnChaves() + 1);
            }
    
            node.setChaves(sibling.getChaves()[0], index);
    
            for (int i = 1; i < sibling.getnChaves(); i++) {
                sibling.setChaves(sibling.getChaves()[i], i - 1);
            }
    
            if (!sibling.isLeaf()) {
                for (int i = 1; i <= sibling.getnChaves(); i++) {
                    sibling.setPonteiro(sibling.getPonteiro()[i], i - 1);
                }
            }
    
            child.setnChaves(child.getnChaves() + sibling.getnChaves() + 1);
            sibling.setnChaves(sibling.getnChaves() - 1);
        }
    
        private void mergeChildNodes(NodeB<T> node, int index) {
            NodeB<T> child = node.getPonteiro()[index];
            NodeB<T> sibling = node.getPonteiro()[index + 1];
    
            child.setChaves(node.getChaves()[index], child.getnChaves());
    
            for (int i = 0; i < sibling.getnChaves(); i++) {
                child.setChaves(sibling.getChaves()[i], child.getnChaves() + 1 + i);
            }
    
            if (!child.isLeaf()) {
                for (int i = 0; i <= sibling.getnChaves(); i++) {
                    child.setPonteiro(sibling.getPonteiro()[i], child.getnChaves() + 1 + i);
                }
            }
    
            for (int i = index + 1; i < node.getnChaves(); i++) {
                node.setChaves(node.getChaves()[i], i - 1);
            }
    
            for (int i = index + 2; i <= node.getnChaves(); i++) {
                node.setPonteiro(node.getPonteiro()[i], i - 1);
            }
    
            child.setnChaves(child.getnChaves() + sibling.getnChaves() + 1);
            node.setnChaves(node.getnChaves() - 1);
    
            node.getPonteiro()[index] = child;
        }
    
        private NodeB<T> findMinValueNode(NodeB<T> node) {
            NodeB<T> currentNode = node;
            while (!currentNode.isLeaf()) {
                currentNode = currentNode.getPonteiro()[0];
            }
            return currentNode;
        }
     
    private void preOrderTraversal(NodeB<T> node) { //letra H
        if (node != null) {
            for (int i = 0; i < node.getnChaves(); i++) {
                System.out.print(node.getChaves()[i] + " ");
            }
            for (int i = 0; i <= node.getnChaves(); i++) {
                preOrderTraversal(node.getPonteiro()[i]);
            }
        }
    }
    public void preOrderTraversal() {
        preOrderTraversal(root);
    }
    
    public void levelOrderTraversal() { // letra G
        if (root == null) {
            System.out.println("A árvore está vazia.");
            return;
        }
        
        int height = getHeight(root);
        for (int i = 1; i <= height; i++) {
            printGivenLevel(root, i);
        }
    }

    private int getHeight(NodeB<T> node) {    // letra E
        if (node == null) {
            return 0;
        } else {
            int maxDepth = 0;
            for (int i = 0; i < node.getnChaves(); i++) {
                int childDepth = getHeight(node.getPonteiro()[i]);
                if (childDepth > maxDepth) {
                    maxDepth = childDepth;
                }
            }
            return maxDepth + 1;
        }
    }

    private void printGivenLevel(NodeB<T> node, int level) {
        if (node == null) {
            return;
        }
        if (level == 1) {
            for (int i = 0; i < node.getnChaves(); i++) {
                System.out.print(node.getChaves()[i] + " ");
            }
        } else if (level > 1) {
            for (int i = 0; i <= node.getnChaves(); i++) {
                printGivenLevel(node.getPonteiro()[i], level - 1);
            }
        }
    }

/////encontrando maior chave(letra C)
        public class Result {
            NodeB<T> node;
            int position;
    
            public Result(NodeB<T> node, int position) {
                this.node = node;
                this.position = position;
            }
        }
    
        public Result findMaxKey() {
            if (root == null || root.getnChaves() == 0) {
                return null; // Árvore vazia
            }
    
            return findMaxKey(root);
        }
    
        private Result findMaxKey(NodeB<T> node) { //letra C
            if (node.isLeaf()) {
                int maxPosition = node.getnChaves() - 1;
                return new Result(node, maxPosition);
            } else {
                int maxChildIndex = node.getnChaves();
                NodeB<T> maxChildNode = node.getPonteiro()[maxChildIndex];
                return findMaxKey(maxChildNode);
            }
        }

            public NodeB<T> findMinKeyNode() { // letra D
        if (root == null || root.getnChaves() == 0) {
            return null; // Árvore vazia
        }

        NodeB<T> currentNode = root;

        while (!currentNode.isLeaf()) {
            currentNode = currentNode.getPonteiro()[0];
        }

        return currentNode;
    }

    /** o usuário definir a ordem:
     * 
     * System.out.print("Digite a ordem da árvore B: ");
        int ordem = scanner.nextInt();

        BTree<Integer> tree = new BTree<>(ordem);

        esta parte será aplicada no main.
        */


        
       
    }
    





