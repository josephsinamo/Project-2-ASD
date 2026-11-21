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

            boolean stat = true;
            while (node != null) {
                
                sTree tree = node.getData();
                
                stat = req(m, tree, true, new sLinkedList<Matakuliah>()) && stat;
                node = node.next;
            }

            if (stat){
                m.addMk(new Matakuliah(new String("SKR;Skripsi;6;8;Keduanya").split(";")));
            }

            m.printKRS();
        }
    }

    public static boolean req(Mahasiswa mhs, sTree tree, boolean sParent, 
        sLinkedList <Matakuliah> listBlmLulus)
    {
        Matakuliah bufMatkul = (Matakuliah)tree.getData();
        boolean wajib = true;

        
        
        boolean canTakeMk = bufMatkul.canTake(mhs.getTgrSem());
        if (canTakeMk && sParent){
            mhs.addMk(bufMatkul);
        }else if(canTakeMk && !sParent){
            mhs.addBlmMk(bufMatkul, listBlmLulus.toString());
        }else if(!sParent){
            wajib = false;
        }
        
        boolean status = mhs.isLulus(bufMatkul.getKode());
        
        if (!status){
            listBlmLulus.add(bufMatkul);
        }
        sLinkedList <sTree>.Node child = tree.getChild().getHead();
        while (child != null){
            wajib = req(mhs, child.getData(), status, listBlmLulus) && wajib  ;
            child = child.next;
        }
        
        listBlmLulus.removeTail();
        return wajib;
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
                
                sTree tes = ((sTree)pair.getValue());
                
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
