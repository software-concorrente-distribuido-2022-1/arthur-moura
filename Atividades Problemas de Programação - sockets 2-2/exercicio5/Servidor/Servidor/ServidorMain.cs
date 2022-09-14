using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Sockets;
using System.Text;
using System.Threading.Tasks;

namespace Servidor
{
    public class ServidorMain
    {
        public readonly int PortaDefault = 8080;
        public void Main(string[] args)
        {
            int porta;
            if (args?.Length == 1)
            {
                porta = Int32.Parse(args[0]);
            }
            else
            {
                porta = PortaDefault;
            }

            TcpListener? SocketServidor;
            while (true)
            {
                try
                {
                    SocketServidor = new TcpListener(porta);
                    break;
                }
                catch (IOException e)
                {
                    porta++;
                }
            }

            Console.WriteLine("\nServidor ativado. " + "Aguardando Cliente na porta " + porta + "...\n");


            Socket? SocketCliente;
            while (true)
            {
                try
                {
                    SocketServidor.Start();
                    SocketCliente = SocketServidor?.AcceptSocket();
                    break;
                }
                catch (IOException e)
                {
                    Console.WriteLine("Erro de E/S " + e);
                    Environment.Exit(1);
                }
            }

            Conexao conexao = new Conexao(SocketCliente!);
            conexao.Start();
        }
    }
}
