package structures;

public record ContainerType(String name, String description) {
    public static final ContainerType cupboard = new ContainerType("Cupboard", "Its a cupboard. When you think of a office cupboard in your head, this exact model is what you think off. How strange.");
    public static final ContainerType toolbox = new ContainerType("Toolbox", "This bright red tool box really sticks out compared to the monotone rest of the facility. Did someone really bring their own toolbox to work?");
    public static final ContainerType hasmatlocker = new ContainerType("Hazmat Locker", "This has a lengthy collection of identical hazmat suits and other lab wear. A sticky note reads '10p fine for not returning!'");
    public static final ContainerType biohazardbox = new ContainerType("Biohazard Box", "A yellow box covered in all manner of warning signs. However, a sticky note tells you that it has now been decommissioned into a scientists lunchbox.");

    public static final ContainerType containmentPod = new ContainerType("Containment Pod", "It's a creepy looking pod with some kind of jelly solution inside.");
    public static final ContainerType gunLocker = new ContainerType("Gun Locker", "Its a secure locker for holding all sorts of weaponry.");
}
