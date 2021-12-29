package c26;

public abstract class InstructionBook {
    public final void create() {
        this.firstProcess();
        this.secondProcess();
    }

    abstract void secondProcess();

    abstract void firstProcess();
}
