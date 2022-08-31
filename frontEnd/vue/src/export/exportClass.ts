export class objectClass{
    private _set: Set<string>;
    constructor(list:any) {

        this._set =new Set();
        const keyList=[];
        list.forEach((item:any)=>{
           keyList.push(item.info.key);
        });





    }

}