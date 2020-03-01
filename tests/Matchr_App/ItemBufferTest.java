package Matchr_App;


public class ItemBufferTest {

    // buffers at 500ms ping
    private static ItemBuffer itemBuffer1 = new ItemBuffer(true, 100);

    // buffers at 1500ms ping
    private static ItemBuffer itemBuffer2 = new ItemBuffer(true, 1000);

    // buffers once batch completes loading
    private static ItemBuffer itemBuffer3 = new ItemBuffer();

    public static void main(String[] args) {

        itemBuffer1.initBufferSimulation();
    }

}