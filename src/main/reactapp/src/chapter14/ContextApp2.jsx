// 0. 컨텍스트 이용한 컴포넌트 트리로 곧바로 전달하기 위한 컨텍스트 저장소 생성
const ThemeContext = React.createContext('light');

// 1. 컴포넌트1 : 조부모
    // Provider를 사용하여 하위 컴포넌트들에게 현재 데이터를 전달
    // 모든 하위 컴포넌트들은 트리 하단에 얼마나 깊이 있는지에 관계 없이 데이터를 호출할 수 있다.
export default function ContextApp2(props){
    return(<>
    <ThemeContext.Provider value="dark">
        <Toolbar/>
    </ThemeContext.Provider>
    </>)
}
// 2. 컴포넌트2 : 부모
    // 중간에 위치한 컴포넌트는 테마 데이터를 하위 컴포넌트로 전달할 필요가 없습니다.
function Toolbar(props){
    return(<>
        <ThemedButton />
    </>)
}
// 3. 컴포넌트3 : 자식
    // 리액트는 가장 가까운 상위 테마 Provider를 찾아서 해당되는 값을 사용합니다.
    // 만약 해당되는 Provider가 없을 경우 기본값을 사용합니다.
    // 여기에서는 상위 Provider가 있기 때문에 현재 테마의 값은 'dark'가 됩니다.
    // Consumer를 사용하여 리액트는 가장 가까운 상위 테마 Provider를 찾아서 해당되는 값을
function ThemedButton(props){
    return(<>
    <ThemeContext.Consumer>
        {value => <Button theme={value}/>}
    </ThemeContext.Consumer>
    </>)
}