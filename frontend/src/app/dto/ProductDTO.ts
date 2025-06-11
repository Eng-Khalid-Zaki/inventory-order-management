export class ProductDTO {
  constructor(
    public id: number,
    public productName: string,
    public modelYear: number,
    public listPrice: number
  ) {}
}
