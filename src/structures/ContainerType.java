package structures;

public record ContainerType(String name, String description) {
    public static ContainerType cupboard = new ContainerType("Cupboard", "Its a cupboard. When you think of a office cupboard in your head, this exact model is what you think off. How strange.");
    public static ContainerType toolbox = new ContainerType("Toolbox", "This bright red tool box really sticks out compared to the monotone rest of the facility. Did someone really bring their own toolbox to work?");
    public static ContainerType containmentpod = new ContainerType("Containment Pod", "It's a creepy looking pod with some kind of jelly solution inside.");
    public static ContainerType gunlocker = new ContainerType("Gun Locker", "Its a secure locker for holding all sorts of weaponry.");
}
