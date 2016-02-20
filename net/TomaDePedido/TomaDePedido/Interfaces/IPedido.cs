namespace TomaDePedido.Interfaces
{
    public interface IPedido
    {
        void AgregarCerveza(int codigoCerveza, int cantidad);

        void AgregarPlato(int codigoPlato, int cantidad, string comentario);
    }
}
