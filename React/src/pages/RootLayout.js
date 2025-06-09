
import { Outlet} from 'react-router-dom';

function RootLayout() {
//   const token = useLoaderData();
//   const submit = useSubmit();
//   // const navigation = useNavigation();
//   useEffect(() => {
//     if (!token) {
//       return;
//     }

//     if (token === 'EXPIRED') {
//       submit(null, { action: '/logout', method: 'post' });
//       return;
//     }

//     const tokenDuration = getTokenDuration();
//     console.log(tokenDuration);

//     setTimeout(() => {
//       submit(null, { action: '/logout', method: 'post' });
//     }, tokenDuration);
//   }, [token, submit]);

  return (
        <Outlet />

  );
}

export default RootLayout;