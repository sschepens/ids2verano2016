namespace TomaDePedido.Interfaces
{
    public interface IPedido
    {
        void GuardarPedido();
        void AgregarPlato(int codigoPlato, int cantidad);
    }
}
