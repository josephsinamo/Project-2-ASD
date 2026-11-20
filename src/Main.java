import dsa.sHashMap;
import dsa.sLinkedList;
import dsa.sTree;
import dsa.sHashMap.Pair;
import dsa.sLinkedList.Node;

public class Main {
    public static void main(String[] args) throws Exception {
        String pathDataMatakuliah = "data/matakuliah.csv";
        String pathDataPrasyarat = "data/prasyarat.csv";
        sLinkedList <sTree> list = inisialisasi(pathDataMatakuliah, pathDataPrasyarat);

        Mahasiswa [] mhs = Mahasiswa.makeMhs("input");
        for(Mahasiswa m: mhs){
            sLinkedList <sTree>.Node node = list.getHead();
            while (node != null) {
                sTree tree = node.getData();
                req(m, tree, true, new sLinkedList<Matakuliah>());
                node = node.next;
            }

            m.printKRS();
        }
    }

    public static void req(Mahasiswa mhs, sTree tree, boolean sParent, 
        sLinkedList <Matakuliah> listBlmLulus)
    {
        Matakuliah bufMatkul = (Matakuliah)tree.getData(); 
        
        if (bufMatkul.canTake(mhs.getTgrSem()) && sParent){
            //System.out.printf("nama Matkul: %s -- apa Lulus parent: %s -- lulus self: %s\n",bufMatkul,status,mhs.isLulus(bufMatkul.getKode()));
            mhs.addMk(bufMatkul);
        }else{
            mhs.addBlmMk(bufMatkul, listBlmLulus);
        }
        
        boolean status = mhs.isLulus(bufMatkul.getKode());
        if (!status){
            listBlmLulus.add(bufMatkul);
        }
        sLinkedList <sTree>.Node child = tree.getChild().getHead();
        while (child != null){
            req(mhs, child.getData(), status, listBlmLulus);
            child = child.next;
        }
    }

    public static sLinkedList <sTree> inisialisasi(String pathMatakuliah, String pathPrasyarat) throws Exception{
        sLinkedList <sTree> list = new sLinkedList<>();
        sHashMap <String, sTree> mapMataKuliah = Matakuliah.makeMataKuliah(pathMatakuliah);
        sLinkedList <Prasyarat> listPraSyarat = Prasyarat.makePrasyarat(pathPrasyarat);

        sLinkedList<Prasyarat>.Node  node = listPraSyarat.getHead();
        while(node != null){
            Prasyarat prasyarat = node.getData();
            sTree root = mapMataKuliah.get(prasyarat.getPraSyarat());
            sTree child = mapMataKuliah.get(prasyarat.getKodeMk());
            root.add(child);

            node = node.next;
        }

        for(sLinkedList <sTree> l : mapMataKuliah.getList()){
            Node n = l.getHead();
            while(n != null){
                Node next = n.next;
                Pair pair= (Pair)n.data;
                // System.out.println(n);
                sTree tes = ((sTree)pair.getValue());
                // System.out.println(tes.getData() +"  "+tes.getIsRoot());
                if (!((sTree)pair.getValue()).getIsRoot()){
                    mapMataKuliah.remove((String)pair.getKey());
                }
                n = next;
            }
        }
        
        list = mapMataKuliah.makeValueList();
        return list;
    }
}
